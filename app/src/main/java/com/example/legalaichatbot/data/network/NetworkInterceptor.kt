package com.legal.aichatbot.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            return chain.proceed(request)
        } catch (e: Exception) {
            throw IOException("Network error: ${e.message}")
        }
    }
}