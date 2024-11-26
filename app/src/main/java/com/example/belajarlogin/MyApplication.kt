package com.example.belajarlogin

import android.app.Application
import com.example.belajarlogin.core.modules.dataStoreModule
import com.example.belajarlogin.core.modules.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Add a module here
            androidContext(this@MyApplication)
            androidLogger(Level.ERROR)
            modules(listOf(dataStoreModule, userModule))
        }
    }
}