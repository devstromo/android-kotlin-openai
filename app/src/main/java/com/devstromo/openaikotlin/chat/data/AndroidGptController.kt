package com.devstromo.openaikotlin.chat.data

import android.util.Log
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.devstromo.openaikotlin.chat.core.config.getOpenAiConfig
import com.devstromo.openaikotlin.chat.domain.GptController

class AndroidGptController() : GptController {
    private val openAI: OpenAI by lazy { getOpenAiConfig() }

    override suspend fun sendMessage(message: String) {
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = listOf(
                ChatMessage(
                    role = ChatRole.System,
                    content = "You are a helpful assistant!"
                ),
                ChatMessage(
                    role = ChatRole.User,
                    content = message
                )
            )
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        Log.i("Chat completion", " Completion message $completion")
    }
}