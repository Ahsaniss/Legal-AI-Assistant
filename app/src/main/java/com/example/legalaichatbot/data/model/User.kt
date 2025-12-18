package com.legal.aichatbot.data.model

data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val preferredLanguage: String = "en" // "en" or "ur"
)