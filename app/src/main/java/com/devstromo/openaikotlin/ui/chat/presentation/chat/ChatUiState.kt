package com.devstromo.openaikotlin.ui.chat.presentation.chat

import com.devstromo.openaikotlin.ui.chat.data.ChatMessage

data class ChatUiState(
    val isLoading: Boolean = false,
    val messages: List<ChatMessage> = emptyList()
)