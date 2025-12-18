package com.legal.aichatbot.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legal.aichatbot.data.model.Message
import com.legal.aichatbot.data.repository.ChatRepository
import com.legal.aichatbot.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository = ChatRepository()
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _currentLanguage = MutableStateFlow("en")
    val currentLanguage: StateFlow<String> = _currentLanguage

    fun setLanguage(language: String) {
        _currentLanguage.value = language
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            // Add user message
            val userMessage = Message(text = text, isUser = true, language = _currentLanguage.value)
            _messages.value = _messages.value + userMessage

            _isLoading.value = true

            // Get AI response
            val result = chatRepository.sendMessage(
                message = text,
                conversationHistory = _messages.value,
                language = _currentLanguage.value
            )

            _isLoading.value = false

            when (result) {
                is NetworkResult.Success -> {
                    val botMessage = Message(
                        text = result.data ?: "No response",
                        isUser = false,
                        language = _currentLanguage.value
                    )
                    _messages.value = _messages.value + botMessage
                }
                is NetworkResult.Error -> {
                    val errorMessage = Message(
                        text = "Error: ${result.message}",
                        isUser = false
                    )
                    _messages.value = _messages.value + errorMessage
                }
                else -> {}
            }
        }
    }

    fun clearChat() {
        _messages.value = emptyList()
    }
}