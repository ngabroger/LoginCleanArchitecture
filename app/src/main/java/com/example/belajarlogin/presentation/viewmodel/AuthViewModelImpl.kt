package com.example.belajarlogin.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belajarlogin.core.utils.ResultUtil
import com.example.belajarlogin.data.model.LoginResponse
import com.example.belajarlogin.domain.usecase.authUseCase.GetTokenUseCase
import com.example.belajarlogin.domain.usecase.authUseCase.GetUserNameCase
import com.example.belajarlogin.domain.usecase.authUseCase.LoginUseCase
import com.example.belajarlogin.domain.usecase.authUseCase.RegisterUseCase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModelImpl(private val getTokenUseCase: GetTokenUseCase, private val loginUseCase: LoginUseCase, private val registerUseCase: RegisterUseCase, private val getUserNameCase: GetUserNameCase) : ViewModel(), AuthViewModel {

    private val _user = MutableStateFlow<ResultUtil<List<LoginResponse>>>(ResultUtil.Idle)
    val user : StateFlow<ResultUtil<List<LoginResponse>>> = _user

    private  val _userName = MutableStateFlow<String?>(null)
    val userName : StateFlow<String?> = _userName

    override suspend fun login(email: String, password: String) {
        viewModelScope.launch{
            loginUseCase(email,password).collect{
                result ->
                _user.value = result
            }
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUserName(): String{
       getUserNameCase().collect{
           result ->
           _userName.value=result

       }
        return userName.value ?: ""
    }


}