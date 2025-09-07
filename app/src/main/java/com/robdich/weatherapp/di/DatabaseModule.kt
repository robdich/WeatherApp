package com.robdich.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.robdich.weatherapp.data.local.AppDatabase
import com.robdich.weatherapp.data.local.WeatherDao
import org.koin.dsl.module

fun provideDataBase(application: Application): AppDatabase =
    Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "weather-db"
    ).build()

fun provideDao(database: AppDatabase): WeatherDao = database.weatherDao()

val databaseModule = module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}