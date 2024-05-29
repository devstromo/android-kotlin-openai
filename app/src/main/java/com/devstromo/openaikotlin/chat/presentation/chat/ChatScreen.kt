package com.devstromo.openaikotlin.chat.presentation.chat


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devstromo.openaikotlin.R
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme
import com.devstromo.openaikotlin.ui.theme.kDarkGrey
import com.devstromo.openaikotlin.ui.theme.kLightGrey
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.FileImage

@Composable
fun ChatScreen(
    state: ChatUiState,
    onSendMessage: (String) -> Unit,
    onInputFinished: () -> Unit
) {
    val chatListState = rememberLazyListState()

    LaunchedEffect(state.messages) {
        chatListState.animateScrollToItem(chatListState.layoutInfo.totalItemsCount)
    }
    // TODO: Resolve list reversed from logic not here
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            state = chatListState,
            reverseLayout = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.messages) { message ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ChatMessageContainer(
                        message = message,
                        modifier = Modifier
                            .align(
                                if (message.isFromChatGTP) Alignment.Start else Alignment.End
                            ),
                        onInputFinished = onInputFinished
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(color = Color.Transparent)
        ) {
            ChatInput(
                onSendMessage = onSendMessage,
                state = state
            )
        }
    }

}

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    state: ChatUiState,
    onSendMessage: (String) -> Unit
) {
    var message by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.DarkGray,
        backgroundColor = Color.DarkGray.copy(alpha = .3f)
    )
    var selectImageUri by remember { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        selectImageUri = uri
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalTextSelectionColors provides customTextSelectionColors
        ) {
            OutlinedTextField(
                value = message,
                enabled = !state.isReceivingResponse,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                leadingIcon = {
                    IconButton(onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                        Icon(
                            FontAwesomeIcons.Regular.FileImage,
                            contentDescription = "Pick image",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        selectImageUri?.let { uri ->

                        }
                        IconButton(onClick = {
                            onSendMessage(message)
                            message = ""
                            selectImageUri = null
                            keyboardController?.hide()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_send),
                                contentDescription = "Send message",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = kLightGrey,
                    unfocusedContainerColor = kLightGrey,
                    cursorColor = kDarkGrey,
                    focusedTextColor = kDarkGrey,
                    disabledContainerColor = kDarkGrey.copy(alpha = .2f),
                    disabledBorderColor = Color.Transparent
                ),
                shape = MaterialTheme.shapes.extraLarge
            )
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    OpenAiKotlinTheme {
        ChatScreen(
            state = ChatUiState(),
            onSendMessage = { _ -> },
            onInputFinished = {}
        )
    }
}

@Preview
@Composable
private fun ChatInputDisablePreview() {
    OpenAiKotlinTheme {
        ChatScreen(
            state = ChatUiState(
                isReceivingResponse = true
            ),
            onSendMessage = { _ -> },
            onInputFinished = {}
        )
    }
}