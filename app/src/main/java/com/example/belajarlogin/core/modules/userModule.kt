package com.example.belajarlogin.core.modules

import com.example.belajarlogin.data.api.ApiConfig
import com.example.belajarlogin.data.api.ApiService
import com.example.belajarlogin.data.repository.AuthRepositoryImpl
import com.example.belajarlogin.data.storage.AuthPreference
import com.example.belajarlogin.domain.repository.AuthRepository
import com.example.belajarlogin.domain.usecase.authUseCase.GetTokenUseCase
import com.example.belajarlogin.domain.usecase.authUseCase.LoginUseCase
import com.example.belajarlogin.domain.usecase.authUseCase.RegisterUseCase
import com.example.belajarlogin.presentation.viewmodel.AuthViewModel
import com.example.belajarlogin.presentation.viewmodel.AuthViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module{
    single<ApiService>{ ApiConfig.getApiService() }
    single<AuthRepository>{ AuthRepositoryImpl(get(), get()) }
    single{ GetTokenUseCase(get()) }
    single{ LoginUseCase(get()) }
    single{ RegisterUseCase(get()) }
    single{ AuthPreference(get()) }

    viewModel{ AuthViewModelImpl(get(), get(), get(),get()) }

}