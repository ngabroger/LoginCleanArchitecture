package com.example.belajarlogin.domain.repository

import com.example.belajarlogin.core.utils.ResultUtil
import com.example.belajarlogin.data.model.LoginResponse
import com.example.belajarlogin.data.model.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<ResultUtil<List<LoginResponse>>>
    suspend fun register(name: String, email: String, password: String): Flow<ResultUtil<List<RegisterResponse>>>
    suspend fun getAuthToken(): String?
}