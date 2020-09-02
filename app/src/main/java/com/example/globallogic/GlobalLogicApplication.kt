package com.example.globallogic

import android.app.Application
import com.example.globallogic.di.globalLogicApp
import com.example.globallogic.di.globalLogicModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GlobalLogicApplication : Application() {

    fun GlobalLogicApplication() {
        instance = this
    }
    companion object {
        private var instance: GlobalLogicApplication? = null
        fun getContext(): GlobalLogicApplication? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@GlobalLogicApplication)
            androidLogger(Level.DEBUG)
            modules(globalLogicApp)
        }
    }
}