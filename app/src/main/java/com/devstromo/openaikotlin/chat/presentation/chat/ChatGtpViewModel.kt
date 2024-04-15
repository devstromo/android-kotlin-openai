package com.devstromo.openaikotlin.chat.presentation.chat

import androidx.lifecycle.ViewModel
import com.devstromo.openaikotlin.chat.data.GPTMessage
import com.devstromo.openaikotlin.chat.domain.GptController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ChatGtpViewModel @Inject constructor(
    private val gptController: GptController
) : ViewModel() {
    private val _state = MutableStateFlow(ChatUiState())
    val state = _state.asStateFlow()

    fun sendMessage(message: GPTMessage) {
        _state.update {
            it.copy(
                messages = it.messages + message
            )
        }
    }
}