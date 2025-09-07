package com.robdich.weatherapp.weather

data class City(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

val cities = listOf(
    City("Manila", 14.5958, 120.9772),
    City("Quezon City", 14.6500, 121.0475),
    City("Taguig City", 14.5200, 121.0500),
    City("Pasig City", 14.5605, 121.0765),
    City("Mandaluyong City", 14.5800, 121.0300)
)