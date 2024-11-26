package com.example.belajarlogin.presentation.viewmodel

import kotlinx.coroutines.flow.Flow

interface AuthViewModel {
    suspend fun login(email: String, password: String)

    suspend fun register(name: String, email: String, password: String)

    suspend fun fetchUserName(): String?

}