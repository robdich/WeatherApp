package com.robdich.weatherapp.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun WeatherScreen() {
    val viewModel = koinViewModel<WeatherViewModel>()
    val state = viewModel.weatherState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp)
    ) {
        DropDownView(
            modifier = Modifier
                .padding(top = 32.dp)
                .align(Alignment.TopStart),
            onCityClick = { city ->
                viewModel.loadWeather(
                    longitude = city.longitude,
                    latitude = city.latitude
                )
            }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            colors = CardDefaults.cardColors(
                containerColor = SurfaceColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            when {
                state.isLoading -> {
                    LoadingView()
                }
                !state.isLoading && state.isError -> {
                    ErrorView()
                }
                else -> {
                    WeatherScreenContent(
                        weather = state.weather
                    )
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun WeatherScreenContent(
    weather: WeatherData
) {
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = weather.city,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )

        Row {
            Text(
                text = weather.getDateString(),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = weather.country,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Gray
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(200.dp),
                model = weather.getImageUrl(),
                contentDescription = "Weather Image"
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format("%.0f°", weather.temp),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = weather.description,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = weather.getSunriseString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "sunrise",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = weather.getSunsetString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "sunset",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun DropDownView(
    modifier: Modifier = Modifier,
    onCityClick: (city: City) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var itemPosition by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                isExpanded = true
            }
        ) {
            Text(
                text = cities[itemPosition].name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "DropDown icon"
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            cities.forEachIndexed { index, city ->
                DropdownMenuItem(
                    text = { Text(text = city.name) },
                    onClick = {
                        isExpanded = false
                        itemPosition = index
                        onCityClick(city)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun WeatherScreenPreview() {
    WeatherScreenContent(
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