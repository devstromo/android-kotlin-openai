package com.devstromo.openaikotlin.ui.chat.presentation.chat


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devstromo.openaikotlin.R
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme
import com.devstromo.openaikotlin.ui.theme.kLightGrey

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
    val message = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                color = kLightGrey,
                shape = ShapeDefaults.ExtraLarge
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message.value,
            onValueChange = { message.value = it },
            modifier = Modifier.weight(1f),
            placeholder = {
                Text(text = "Type input")
            }
        )
        IconButton(onClick = {
            keyboardController?.hide()
        }) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "send icon"
            )
        }
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