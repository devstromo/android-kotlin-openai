package com.devstromo.openaikotlin.chat.data

import android.net.Uri
import java.util.UUID

data class GPTMessage(
    val id: String = UUID.randomUUID().toString(),
    val message: String,
    val isFromChatGTP: Boolean,
    val uri: Uri? = null,
)

