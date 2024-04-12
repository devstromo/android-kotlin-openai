package com.devstromo.openaikotlin.chat.core.config

import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAI
import com.devstromo.openaikotlin.BuildConfig
import kotlin.time.Duration.Companion.seconds

fun getOpenAiConfig(): OpenAI {
    val apiKey = BuildConfig.OPENAI_API_KEY
    return OpenAI(
        token = apiKey,
        timeout = Timeout(socket = 60.seconds)
    )
}