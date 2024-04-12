package com.devstromo.openaikotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devstromo.openaikotlin.ui.chat.presentation.chat.ChatScreen
import com.devstromo.openaikotlin.ui.chat.presentation.chat.ChatUiState
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenAiKotlinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(
                        state = ChatUiState(),
                        onSendMessage = { message ->

                        }

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    OpenAiKotlinTheme {
        ChatScreen(
            ChatUiState(),
            onSendMessage = {}
        )
    }
}