package com.example.belajarlogin.presentation.viewmodel

interface AuthViewModel {
    suspend fun login(email: String, password: String)

    suspend fun register(name: String, email: String, password: String)

    suspend fun fetchToken(): String?

}