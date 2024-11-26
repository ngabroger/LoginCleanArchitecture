package com.example.belajarlogin.domain.usecase.authUseCase

import com.example.belajarlogin.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend fun logout() {
        authRepository.logout()
    }

}