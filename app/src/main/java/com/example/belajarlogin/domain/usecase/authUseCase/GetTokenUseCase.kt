package com.example.belajarlogin.domain.usecase.authUseCase

import com.example.belajarlogin.domain.repository.AuthRepository

class GetTokenUseCase (private val authRepository: AuthRepository){
    suspend fun getToken(): String? {
        return authRepository.getAuthToken()
    }



}