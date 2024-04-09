package com.devstromo.openaikotlin.ui.chat.presentation.chat


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
) {
    val prompt by remember {
        mutableStateOf("A brown fox on the ground")
    }

    Text(
        text = "Image Prompt $prompt!",
        modifier = modifier
    )
}