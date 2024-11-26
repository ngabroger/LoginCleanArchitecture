package com.example.belajarlogin.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    suspend fun saveToken(token: String,name: String)
    suspend fun getToken(): String?
    suspend fun getName(): Flow<String?>
}