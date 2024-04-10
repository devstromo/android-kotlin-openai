package com.devstromo.openaikotlin.ui.chat.presentation.chat


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devstromo.openaikotlin.R
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
) {
    val prompt by remember {
        mutableStateOf("A brown fox on the ground")
    }

    Column(
        modifier = modifier
            .padding(10.dp)
    ) {
        Text(
            text = "Image Prompt $prompt!",
        )
        ChatInput()
    }

}

@Composable
fun ChatInput(
    modifier: Modifier = Modifier
) {
    var message by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Transparent,
                shape = ShapeDefaults.ExtraLarge
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .weight(1f),
            trailingIcon = {
                IconButton(onClick = {
                    message = ""
                    keyboardController?.hide()
                }) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "send icon"
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.extraLarge
        )
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    OpenAiKotlinTheme {
        ChatScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}