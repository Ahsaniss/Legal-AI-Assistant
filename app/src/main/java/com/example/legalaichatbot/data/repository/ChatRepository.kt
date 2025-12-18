package com.legal.aichatbot.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.legal.aichatbot.BuildConfig
import com.legal.aichatbot.data.model.Message
import com.legal.aichatbot.utils.Constants
import com.legal.aichatbot.utils.NetworkResult

class ChatRepository {

    private val generativeModel = GenerativeModel(
        modelName = Constants.GEMINI_MODEL,
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun sendMessage(
        message: String,
        conversationHistory: List<Message>,
        language: String
    ): NetworkResult<String> {
        return try {
            val systemPrompt = if (language == "ur") {
                Constants.SYSTEM_PROMPT_UR
            } else {
                Constants.SYSTEM_PROMPT_EN
            }

            val chat = generativeModel.startChat(
                history = conversationHistory.map { msg ->
                    content(if (msg.isUser) "user" else "model") {
                        text(msg.text)
                    }
                }
            )

            val prompt = "$systemPrompt\n\nUser question: $message"
            val response = chat.sendMessage(prompt)

            NetworkResult.Success(response.text ?: "No response generated")
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to get response")
        }
    }
}