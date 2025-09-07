package com.robdich.weatherapp.data.model

import com.robdich.weatherapp.model.WeatherData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind?,
    val rain: Rain?,
    val clouds: Clouds?,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long,
)

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double,
)

@Serializable
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)

@Serializable
data class Main(
    val temp: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("temp_min")
    val tempMin: Double,
    @SerialName("temp_max")
    val tempMax: Double,
    val pressure: Long,
    val humidity: Long,
    @SerialName("sea_level")
    val seaLevel: Long,
    @SerialName("grnd_level")
    val grndLevel: Long,
)

@Serializable
data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double?,
)

@Serializable
data class Rain(
    @SerialName("1h")
    val n1h: Double,
)

@Serializable
data class Clouds(
    val all: Long,
)

@Serializable
data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)

fun WeatherResponse.toWeatherData() = WeatherData(
    city = name,
    country = sys.country,
    date = dt,
    temp = main.temp,
    sunrise = sys.sunrise,
    sunset = sys.sunset,
    weatherIcon = if (weather.isEmpty()) "" else weather[0].icon,
    condition = if (weather.isEmpty()) "" else weather[0].main,
    description = if (weather.isEmpty()) "" else weather[0].description
)
