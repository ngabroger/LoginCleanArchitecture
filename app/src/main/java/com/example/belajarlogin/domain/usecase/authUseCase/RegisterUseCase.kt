package com.example.belajarlogin.domain.usecase.authUseCase

import com.example.belajarlogin.core.utils.ResultUtil
import com.example.belajarlogin.data.model.RegisterResponse
import com.example.belajarlogin.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String) :  Flow<ResultUtil<List<RegisterResponse>>> {
        return authRepository.register(name, email, password)
    }
}