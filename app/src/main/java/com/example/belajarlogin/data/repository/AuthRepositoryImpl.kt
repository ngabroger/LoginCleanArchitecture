package com.example.belajarlogin.data.repository

import android.util.Log
import com.example.belajarlogin.core.utils.ResultUtil
import com.example.belajarlogin.data.api.ApiService
import com.example.belajarlogin.data.model.LoginResponse
import com.example.belajarlogin.data.model.RegisterResponse
import com.example.belajarlogin.domain.repository.AuthRepository
import com.example.belajarlogin.domain.repository.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class AuthRepositoryImpl(private val apiService: ApiService, private val tokenStorage: TokenStorage) : AuthRepository {


    override suspend fun login(
        email: String,
        password: String
    ): Flow<ResultUtil<List<LoginResponse>>> {
        return flow {
            try {
                emit(ResultUtil.Loading)
                val response = apiService.login(email,password)
                if (response.isSuccessful){
                    val data = response.body()?.loginResult
                    if (data == null){
                        emit(ResultUtil.Error("Login Failed"))
                    }
                    data?.token?.let {
                        tokenStorage.saveToken(it.toString(),data.name.toString())
                    }?: run {
                        emit(ResultUtil.Error("Login Failed"))
                        return@flow
                    }
                    emit(ResultUtil.Success(listOf()))

                }else{
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (!errorBody.isNullOrEmpty()) {
                        try {

                            val jsonObject = JSONObject(errorBody)
                            val message = jsonObject.optJSONObject("error")?.optString("message") ?: "Unknown error"
                            message
                        } catch (e: Exception) {
                            "Error occurred while parsing error body"
                        }
                    } else {
                        "Login Failed: No Error Body"
                    }
                    emit(ResultUtil.Error(errorMessage))
                }
            }catch (e: Exception){
                Log.e("LoginRepositoryImpl", "Exception: ${e.message}", e)
                emit(ResultUtil.Error("Exception occurred: ${e.message}"))

            }
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Flow<ResultUtil<List<RegisterResponse>>> {
        return flow{
            try {
                emit(ResultUtil.Loading)
                val response = apiService.register(name,email,password)
                if (response.isSuccessful){
                    val data = response.body()
                    if (data==null){
                        emit(ResultUtil.Error("Register Failed"))
                    }
                }
            }catch (e: Exception){
                emit(ResultUtil.Error("Exception occurred: ${e.message}"))
            }
        }
    }

    override suspend fun getAuthToken(): String? {
        return tokenStorage.getToken()
    }

    override suspend fun getName(): Flow<String?> {
        return tokenStorage.getName()
    }
}