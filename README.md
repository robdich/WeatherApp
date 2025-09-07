## Weather App

Weather app for Android developed using technologies and best practices such as:
 - **Kotlin**
 - **Compose** for UI
 - **Ktor** for network calls
 - **Room** for database
 - **Koin** for dependency injection
 - **Coil** for image loading
 - **MVVM and Clean Architecture** for app architecture

<p float="left">
  <img src="https://github.com/robdich/WeatherApp/blob/main/WeatherHome.png" width="200"/>
  <img src="https://github.com/robdich/WeatherApp/blob/main/WeatherHistory.png" width="200"/>
</p>


### API
Used OpenWeatherMap for weather data.


#### API Key
Add API key to [WeatherApiService.kt](https://github.com/robdich/WeatherApp/blob/main/app/src/main/java/com/robdich/weatherapp/data/network/WeatherApiService.kt)
```
private const val API_KEY = "your_api_key_here"
```
