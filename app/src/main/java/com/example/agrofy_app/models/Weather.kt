package com.example.agrofy_app.models

data class Weather(
    val time: String,
    val temperature: Int,
    val icon: String
)

data class ForecastResponse(
    val list: List<WeatherData>,
    val city: City
)

data class WeatherData(
    val dt: Long,
    val main: Main,
    val weather: List<WeatherDetail>,
    val dt_txt: String
)

data class City(
    val name: String,
    val country: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val humidity: Int
)

data class WeatherDetail(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)