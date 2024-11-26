package com.example.belajarlogin.core.modules

import com.example.belajarlogin.data.storage.AuthPreference
import com.example.belajarlogin.data.storage.DataStoreFactory.createDataStore
import com.example.belajarlogin.domain.repository.TokenStorage
import org.koin.dsl.module

val dataStoreModule = module{
    single{createDataStore(get(), "auth_prefs")}
    single<TokenStorage>{ AuthPreference(get()) }
}