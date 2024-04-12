package com.devstromo.openaikotlin.chat.presentation.chat

import com.devstromo.openaikotlin.chat.data.GPTMessage

data class ChatUiState(
    val isLoading: Boolean = false,
    val messages: List<GPTMessage> = emptyList()
)