package com.devstromo.openaikotlin.chat.domain

interface GptController {

    suspend fun sendMessage(message: String) : String
}