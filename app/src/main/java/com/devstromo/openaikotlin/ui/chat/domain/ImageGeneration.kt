package com.devstromo.openaikotlin.ui.chat.domain

import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.ImageURL
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.devstromo.openaikotlin.ui.chat.core.config.getOpenAiConfig

class ImageGeneration {

    private val openAI: OpenAI by lazy { getOpenAiConfig() }

    suspend fun generate(prompt: String) :List<ImageURL> {
        return openAI.imageURL(
            creation = ImageCreation(
                prompt = prompt,
                model = ModelId("dall-e-3"),
                n = 1,
                size = ImageSize.is1024x1024
            )
        )
    }
}