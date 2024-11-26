package com.example.belajarlogin.data.api

import com.example.belajarlogin.data.model.LoginResponse
import retrofit2.Response

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("email") email:String , @Field("password") password:String): Response<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@Field("name") name:String , @Field("email") email:String , @Field("password") password:String): Response<LoginResponse>
}