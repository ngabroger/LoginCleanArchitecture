package com.example.belajarlogin.data.api

import com.example.belajarlogin.BuildConfig
import com.example.belajarlogin.data.storage.AuthPreference
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.mp.KoinPlatform.getKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {


    fun getApiService(): ApiService{
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val authPreferences: AuthPreference = getKoin().get()
        val authInterceptor = AuthInterceptor(authPreferences)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
        if (!client.interceptors().contains(authInterceptor)){
            client.addInterceptor(authInterceptor)
        }
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.DICODING_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ApiService::class.java)

    }
}