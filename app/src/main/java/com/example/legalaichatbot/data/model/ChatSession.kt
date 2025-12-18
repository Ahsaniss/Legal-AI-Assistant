package com.legal.aichatbot.data.model

data class ChatSession(
    val id: String = java.util.UUID.randomUUID().toString(),
    val messages: List<Message> = emptyList(),
    val category: LegalCategory? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class LegalCategory(val displayNameEn: String, val displayNameUr: String) {
    FAMILY_LAW("Family Law", "خاندانی قانون"),
    LABOR_LAW("Labor Law", "لیبر قانون"),
    CRIMINAL_LAW("Criminal Law", "فوجداری قانون"),
    PROPERTY_LAW("Property Law", "جائیداد کا قانون"),
    CONTRACT_LAW("Contract Law", "معاہدہ قانون"),
    BUSINESS_LAW("Business Law", "کاروباری قانون"),
    GENERAL("General Legal Query", "عمومی قانونی سوال")
}