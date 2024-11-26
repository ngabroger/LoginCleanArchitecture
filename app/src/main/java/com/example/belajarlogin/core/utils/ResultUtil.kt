package com.example.belajarlogin.core.utils

sealed class ResultUtil<out R> {
    data class Success<out T>(val data: T) : ResultUtil<T>()
    data class Error(val exception: String) : ResultUtil<Nothing>()
    object Loading : ResultUtil<Nothing>()
    object Idle : ResultUtil<Nothing>()


}