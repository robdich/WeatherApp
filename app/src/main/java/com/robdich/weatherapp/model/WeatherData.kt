package com.robdich.weatherapp.model

import com.robdich.weatherapp.data.local.WeatherEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class WeatherData(
    val city: String = "",
    val country: String = "",
    val date: Long = 0,
    val temp: Double = 0.0,
    val sunrise: Long = 0,
    val sunset: Long = 0,
    val weatherIcon: String = "",
    val condition: String = "",
    val description: String = ""
) {

    fun getDateString() = date.toFormattedDate()

    fun getSunriseString() = sunrise.toFormattedTime()

    fun getSunsetString() = sunset.toFormattedTime()

    fun getImageUrl() = "https://openweathermap.org/img/wn/${weatherIcon}@4x.png"

    fun getIconUrl() = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png"
}

fun Long.toFormattedDate(): String {
    val date = Date(this * 1000L)
    val outputDateFormat = SimpleDateFormat("MMM d", Locale.getDefault())
    return try {
        outputDateFormat.format(date)
    } catch (_: Exception) {
        "Jan 1"
    }
}

fun Long.toFormattedTime(): String {
    val date = Date(this * 1000L)
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(date)
}

fun WeatherData.toEntity() = WeatherEntity(
    city = city,
    country = country,
    date = date,
    temp = temp,
    sunrise = sunrise,
    sunset = sunset,
    weatherIcon = weatherIcon,
    condition = condition,
    description = description
)

fun WeatherEntity.toData() = WeatherData(
    city = city,
    country = country,
    date = date,
    temp = temp,
    sunrise = sunrise,
    sunset = sunset,
    weatherIcon = weatherIcon,
    condition = condition,
    description = description
)
