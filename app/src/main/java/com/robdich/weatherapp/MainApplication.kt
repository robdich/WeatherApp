package com.robdich.weatherapp

import android.app.Application
import com.robdich.weatherapp.di.appModule
import com.robdich.weatherapp.di.databaseModule
import com.robdich.weatherapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, networkModule, databaseModule)
        }
    }
}