package com.devstromo.openaikotlin.chat.presentation.chat

import androidx.lifecycle.ViewModel
import com.devstromo.openaikotlin.chat.domain.GptController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class ChatGtpViewModel @Inject constructor(
    private val gptController: GptController
) : ViewModel() {
    private val _state = MutableStateFlow(ChatUiState())
}