package com.example.belajarlogin.domain.usecase.authUseCase

import com.example.belajarlogin.core.utils.ResultUtil
import com.example.belajarlogin.data.model.LoginResponse
import com.example.belajarlogin.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<ResultUtil<List<LoginResponse>>> {
        return authRepository.login(email, password)
    }
}