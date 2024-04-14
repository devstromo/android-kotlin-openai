package com.devstromo.openaikotlin.chat.data

import com.aallam.openai.client.OpenAI
import com.devstromo.openaikotlin.chat.core.config.getOpenAiConfig
import com.devstromo.openaikotlin.chat.domain.GptController

class AndroidGptController() : GptController {
    private val openAI: OpenAI by lazy { getOpenAiConfig() }

    override suspend fun sendMessage(message: String) {
        TODO("Not yet implemented")
    }
}