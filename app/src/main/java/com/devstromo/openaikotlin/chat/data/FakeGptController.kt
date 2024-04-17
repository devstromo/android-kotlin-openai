package com.devstromo.openaikotlin.chat.data

import com.devstromo.openaikotlin.chat.domain.GptController

class FakeGptController : GptController {
    override suspend fun sendMessage(message: String): String = """
        Dear [Boss's Name],
        Congratulations on your recent success! Your dedication and hard work have not gone unnoticed, and your leadership is truly inspiring to us all. We are incredibly grateful to have you as our boss, and we look forward to many more achievements under your guidance.
        Thank you for your support and for always pushing us to do our best. Your passion and commitment are truly admirable, and we are lucky to have you leading our team.
        Congratulations once again, and here's to many more accomplishments in the future!
        Sincerely,
        [Your Name]
    """.trimIndent()
}