package com.devstromo.openaikotlin.ui.chat.presentation.chat


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devstromo.openaikotlin.ui.theme.OpenAiKotlinTheme

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier
) {
    val prompt by remember {
        mutableStateOf("A brown fox on the ground")
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Image Prompt $prompt!",
        )
    }

}


@Preview
@Composable
private fun ChatScreenPreview() {
    OpenAiKotlinTheme {
        ChatScreen(
            modifier = Modifier.fillMaxSize()
        )
    }

}