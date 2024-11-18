package com.example.agrofy_app.data.api

import com.example.agrofy_app.models.ForecastResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast")
    suspend fun getForecastByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "id",
        @Query("cnt") count: Int = 40
    ): ForecastResponse
}

object RetrofitInstance {
    // Menggunakan api dari web openweathermap.org
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val api: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}