package com.robdich.weatherapp.repository

import android.util.Log
import com.robdich.weatherapp.data.local.WeatherDao
import com.robdich.weatherapp.data.network.WeatherApiService
import com.robdich.weatherapp.model.WeatherData
import com.robdich.weatherapp.model.toEntity

class WeatherRepository(
    private val apiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {
    suspend fun getWeather(
        longitude: Double,
        latitude: Double
    ): Result<WeatherData> {
        return try {
            val response = apiService.getWeather(longitude = longitude, latitude = latitude)
            weatherDao.insert(response.toEntity())
            Result.success(response)
        } catch (e: Exception) {
            Log.e("WeatherApp", "Error: $e")
            Result.failure(e)
        }
    }
}