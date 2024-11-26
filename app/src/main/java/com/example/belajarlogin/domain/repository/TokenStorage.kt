package com.example.belajarlogin.domain.repository

interface TokenStorage {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
}