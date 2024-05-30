package com.devstromo.openaikotlin.chat.presentation.chat

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devstromo.openaikotlin.chat.data.GPTMessage
import com.devstromo.openaikotlin.chat.presentation.chat.components.TypewriterText
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme
import com.devstromo.openaikotlin.ui.theme.kLightGrey

@Composable
fun ChatMessageContainer(
    message: GPTMessage,
    modifier: Modifier = Modifier,
    onInputFinished: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = if (message.isFromChatGTP) 0.dp else 15.dp,
                    topEnd = 15.dp,
                    bottomStart = 15.dp,
                    bottomEnd = if (message.isFromChatGTP) 15.dp else 0.dp,
                )
            )
            .background(
                kLightGrey
            )
            .padding(16.dp)
    ) {
        Text(
            text = if (message.isFromChatGTP) "GTP" else "Me",
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            color = Color.Black
        )
        if (message.isFromChatGTP) {
            TypewriterText(
                texts = listOf(message.message),
                onInputFinished = onInputFinished,
            )
        } else {
            Text(
                text = message.message,
                color = Color.Black,
                modifier = Modifier.widthIn(max = 250.dp)
            )
        }
    }
}

@Preview
@Composable
fun ChatMessagePreview(
    @PreviewParameter(SampleGPTMessageProvider::class) chat: GPTMessage
) {
    OpenAiKotlinTheme {
        ChatMessageContainer(
            message = GPTMessage(
                id = "1",
                message = chat.message,
                chat.isFromChatGTP,
            ),
            onInputFinished = {}
        )
    }

}

class SampleGPTMessageProvider : PreviewParameterProvider<GPTMessage> {
    override val values = sequenceOf(
        GPTMessage("1", "Hello World", false),
        GPTMessage("2", "Not for me 🙄", true)
    )
}