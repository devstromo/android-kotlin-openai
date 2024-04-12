package com.devstromo.openaikotlin.chat.data

data class GPTMessage(
    val message: String,
    val isFromChatGTP: Boolean,
)
