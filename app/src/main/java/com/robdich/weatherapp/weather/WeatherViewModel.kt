package com.robdich.weatherapp.weather

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robdich.weatherapp.model.WeatherData
import com.robdich.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherState = mutableStateOf(WeatherViewState())
    val weatherState: State<WeatherViewState> get() = _weatherState

    init {
        Log.i("WeatherApp", "Load Weather")
        val defaultCity = cities[0]
        loadWeather(
            longitude = defaultCity.longitude,
            latitude = defaultCity.latitude
        )
    }

    fun loadWeather(
        longitude: Double,
        latitude: Double
    ) = viewModelScope.launch {
        _weatherState.value = weatherState.value.copy(
            isLoading = true
        )

        val result = repository.getWeather(
            longitude = longitude,
            latitude = latitude
        )
        when {
            result.isSuccess -> {
                val weather = result.getOrDefault(WeatherData())
                _weatherState.value = _weatherState.value.copy(
                    weather = weather,
                    isLoading = false,
                    isError = false
                )
            }
            result.isFailure -> {
                _weatherState.value = _weatherState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    data class WeatherViewState(
        val weather: WeatherData = WeatherData(),
        val isLoading:Boolean = true,
        val isError: Boolean = false
    )
}