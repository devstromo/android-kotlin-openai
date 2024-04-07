package com.devstromo.openaikotlin.ui.chat.presentation.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ChatScreen() {
    val prompt by remember {
        mutableStateOf("A brown fox on the ground")
    }
}