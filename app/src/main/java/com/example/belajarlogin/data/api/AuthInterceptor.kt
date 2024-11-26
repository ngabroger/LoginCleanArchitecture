package com.example.belajarlogin.data.api

import com.example.belajarlogin.data.storage.AuthPreference
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (private val authPreference: AuthPreference) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${runBlocking{ authPreference.getToken()}}")
            .build()
        return chain.proceed(newRequest)
    }
}