package com.robdich.weatherapp.repository

import com.robdich.weatherapp.data.local.WeatherDao
import com.robdich.weatherapp.model.WeatherData
import com.robdich.weatherapp.model.toData

class HistoryRepository(
    private val weatherDao: WeatherDao
) {
    suspend fun getWeatherList(): Result<List<WeatherData>> {
        return try {
            val weatherList = weatherDao.getAll().map { it.toData() }
            Result.success(weatherList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}