package com.devstromo.openaikotlin.ui.chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstromo.openaikotlin.ui.chat.data.GPTMessage
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme
import com.devstromo.openaikotlin.ui.theme.kLightGrey

@Composable
fun ChatMessage(
    message: GPTMessage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = if (message.isFromChatGTP) 15.dp else 0.dp,
                    topEnd = 15.dp,
                    bottomStart = 15.dp,
                    bottomEnd = if (message.isFromChatGTP) 0.dp else 15.dp,
                )
            )
            .background(
                if (message.isFromChatGTP) kLightGrey else Color.Blue
            )
            .padding(16.dp)
    ) {
        Text(
            text = message.message,
            fontSize = 10.sp,
            color = Color.Black
        )
        Text(
            text = message.message,
            color = Color.Black,
            modifier = Modifier.widthIn(max = 250.dp)
        )

    }

}

@Preview
@Composable
fun ChatMessagePreview() {
    OpenAiKotlinTheme {
        ChatMessage(
            message = GPTMessage(
                message = "Hello World",
                false
            )
        )
    }

}