package com.example.icr_sdk

import android.app.Application
import com.example.icr_di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ICRApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ICRApplicationClass)
            modules(appModules)
        }

    }
}