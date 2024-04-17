package com.devstromo.openaikotlin.chat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devstromo.openaikotlin.chat.core.AndroidController
import com.devstromo.openaikotlin.chat.core.FakeController
import com.devstromo.openaikotlin.chat.data.GPTMessage
import com.devstromo.openaikotlin.chat.domain.GptController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatGtpViewModel @Inject constructor(
    @FakeController private val gptController: GptController
) : ViewModel() {
    private val _state = MutableStateFlow(ChatUiState())
    val state = _state.asStateFlow()

    fun sendMessage(message: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    messages = it.messages + GPTMessage(message, false)
                )
            }
            val result = gptController.sendMessage(message)
            _state.update {
                it.copy(
                    messages = it.messages + GPTMessage(result, true)
                )
            }
        }
    }
}