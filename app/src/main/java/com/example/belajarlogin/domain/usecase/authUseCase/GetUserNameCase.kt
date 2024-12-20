package com.example.belajarlogin.domain.usecase.authUseCase

import com.example.belajarlogin.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetUserNameCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Flow<String?>{
        return authRepository.getName()
    }
}