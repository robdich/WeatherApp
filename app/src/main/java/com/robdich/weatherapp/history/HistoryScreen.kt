package com.robdich.weatherapp.history

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.robdich.weatherapp.components.ErrorView
import com.robdich.weatherapp.components.LoadingView
import com.robdich.weatherapp.model.WeatherData
import com.robdich.weatherapp.ui.theme.BackgroundColor
import com.robdich.weatherapp.ui.theme.SurfaceColor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoryScreen(
    contentPadding: PaddingValues
) {
    val viewModel = koinViewModel<HistoryViewModel>()
    val state = viewModel.weatherListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        when {
            state.isLoading -> {
                LoadingView()
            }
            !state.isLoading && state.isError -> {
                ErrorView()
            }
            else -> {
                HistoryScreenContent(
                    weatherList = state.weatherList,
                    contentPadding = contentPadding
                )
            }
        }
    }
}

@Composable
fun HistoryScreenContent(
    weatherList: List<WeatherData>,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(weatherList) { weather ->
            HistoryItem(weather = weather)
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
private fun HistoryItem(weather: WeatherData) {
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = weather.city
                )

                Text(
                    text = weather.getDateString(),
                    color = Color.Gray
                )
            }

            Text(
                modifier = Modifier.weight(1f),
                text = String.format("%.0f°", weather.temp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.weight(2f),
                text = weather.description
            )

            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = weather.getIconUrl(),
                contentDescription = "Weather Icon"
            )
        }
    }
}

@Composable
@Preview
fun HistoryScreenPreview() {
    HistoryItem(
        weather = WeatherData(
            city = "Manila",
            country = "PH",
            date = 1757149662,
            temp = 26.26,
            sunrise = 1757108670,
            sunset = 1757153101,
            weatherIcon = "04n",
            condition = "Clouds",
            description = "overcast clouds"
        )
    )
}