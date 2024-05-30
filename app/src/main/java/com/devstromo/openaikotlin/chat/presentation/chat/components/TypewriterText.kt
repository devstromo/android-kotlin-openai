package com.devstromo.openaikotlin.chat.presentation.chat.components

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.streams.toList

@Composable
fun TypewriterText(
    texts: List<String>,
    onInputFinished: () -> Unit
) {
    var textIndex by remember {
        mutableIntStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }
    val textCharsList: List<List<String>> = remember {
        texts.map {
            it.splitToCodePoints()
        }
    }

    LaunchedEffect(
        key1 = texts.joinToString(), // Unique key based on the text content
    ) {
        while (textIndex < textCharsList.size) {
            textCharsList[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = textCharsList[textIndex]
                    .take(
                        n = charIndex + 1,
                    ).joinToString(
                        separator = "",
                    )
                delay(45)
            }
            textIndex += 1
        }
        onInputFinished()
    }

    Text(
        text = textToDisplay,
        color = Color.Black,
        modifier = Modifier.widthIn(max = 250.dp)
    )
}

fun String.splitToCodePoints(): List<String> {
    return codePoints()
        .toList()
        .map {
            String(Character.toChars(it))
        }
}