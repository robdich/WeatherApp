package com.robdich.weatherapp.di

import com.robdich.weatherapp.data.network.WeatherApiService
import com.robdich.weatherapp.history.HistoryViewModel
import com.robdich.weatherapp.repository.HistoryRepository
import com.robdich.weatherapp.repository.WeatherRepository
import com.robdich.weatherapp.weather.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<WeatherApiService> { WeatherApiService(httpClient = get()) }

    single<WeatherRepository> { WeatherRepository(
        apiService = get(),
        weatherDao = get()
    ) }
    viewModel { WeatherViewModel(repository = get()) }

    single<HistoryRepository> { HistoryRepository(weatherDao = get()) }
    viewModel { HistoryViewModel(repository = get()) }
}