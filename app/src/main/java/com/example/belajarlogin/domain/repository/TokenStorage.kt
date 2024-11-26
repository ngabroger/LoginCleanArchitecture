package com.example.belajarlogin.domain.repository

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    suspend fun saveToken(token: String,name: String)
    suspend fun getToken(): Flow<String?>
    suspend fun getName(): Flow<String?>
    suspend fun clearToken()
}