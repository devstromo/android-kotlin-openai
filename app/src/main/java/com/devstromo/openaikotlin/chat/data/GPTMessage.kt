package com.devstromo.openaikotlin.chat.data

import java.util.UUID

data class GPTMessage(
    val id: String = UUID.randomUUID().toString(),
    val message: String,
    val isFromChatGTP: Boolean
)

