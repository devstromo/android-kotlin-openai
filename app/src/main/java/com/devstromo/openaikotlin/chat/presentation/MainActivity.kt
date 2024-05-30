package com.devstromo.openaikotlin.chat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.devstromo.openaikotlin.chat.presentation.chat.ChatGtpViewModel
import com.devstromo.openaikotlin.chat.presentation.chat.ChatScreen
import com.devstromo.openaikotlin.chat.presentation.chat.ChatUiState
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenAiKotlinTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                val viewModel = hiltViewModel<ChatGtpViewModel>()
                val state by viewModel.state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(
                        state = state,
                        onSendMessage = viewModel::sendMessage,
                        onInputFinished = viewModel::onInputFinished
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
            onSendMessage = { _, _ -> },
            onInputFinished = {}
        )
    }
}