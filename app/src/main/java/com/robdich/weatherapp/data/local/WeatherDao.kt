package com.robdich.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    suspend fun getAll(): List<WeatherEntity>

    @Insert
    suspend fun insert(weather: WeatherEntity)
}