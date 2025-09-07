package com.robdich.weatherapp.data.network

import com.robdich.weatherapp.data.model.WeatherResponse
import com.robdich.weatherapp.data.model.toWeatherData
import com.robdich.weatherapp.model.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class WeatherApiService(private val httpClient: HttpClient) {

    suspend fun getWeather(
        longitude: Double,
        latitude: Double
    ): WeatherData {
        return httpClient.get(ENDPOINT) {
            url {
                parameters.append("lon", longitude.toString())
                parameters.append("lat", latitude.toString())
                parameters.append("units", UNIT)
                parameters.append("appid", API_KEY)
            }
            contentType(ContentType.Application.Json)
        }.body<WeatherResponse>().toWeatherData()
    }

    companion object {
        private const val ENDPOINT = "https://api.openweathermap.org/data/2.5/weather/"
        private const val UNIT = "metric"
        private const val API_KEY = "your_api_key_here"
    }
}