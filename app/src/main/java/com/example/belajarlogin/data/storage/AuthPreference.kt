package com.example.belajarlogin.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.belajarlogin.domain.repository.TokenStorage
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


class AuthPreference (private val dataStore: DataStore<Preferences>): TokenStorage {

    companion object {
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
    }
    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[AUTH_TOKEN_KEY] = token
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map {
            it[AUTH_TOKEN_KEY]
        }.firstOrNull()
    }
}