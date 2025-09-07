package com.robdich.weatherapp.history

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robdich.weatherapp.model.WeatherData
import com.robdich.weatherapp.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _weatherListState = mutableStateOf(HistoryViewState())
    val weatherListState: State<HistoryViewState> get() = _weatherListState

    init {
        Log.i("WeatherApp", "Load History")
        loadHistory()
    }

    fun loadHistory(
    ) = viewModelScope.launch {
        _weatherListState.value = weatherListState.value.copy(
            isLoading = true
        )

        val result = repository.getWeatherList()
        when {
            result.isSuccess -> {
                val weatherList = result.getOrDefault(emptyList())
                _weatherListState.value = _weatherListState.value.copy(
                    weatherList = weatherList,
                    isLoading = false,
                    isError = false
                )
            }
            result.isFailure -> {
                _weatherListState.value = _weatherListState.value.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    data class HistoryViewState(
        val weatherList: List<WeatherData> = emptyList(),
        val isLoading:Boolean = false,
        val isError: Boolean = false
    )
}