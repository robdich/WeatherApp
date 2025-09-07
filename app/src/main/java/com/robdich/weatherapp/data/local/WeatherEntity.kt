package com.robdich.weatherapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val city: String,
    val country: String,
    val date: Long,
    val temp: Double,
    val sunrise: Long,
    val sunset: Long,
    @ColumnInfo(name = "weather_icon") val weatherIcon: String,
    val condition: String,
    val description: String
)