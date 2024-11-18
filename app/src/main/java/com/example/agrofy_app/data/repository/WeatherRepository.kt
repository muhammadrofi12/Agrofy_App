package com.example.agrofy_app.data.repository

import com.example.agrofy_app.data.api.RetrofitInstance
import com.example.agrofy_app.models.ForecastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WeatherRepository {
    private const val API_KEY = "45e9a25e6d78503fe964cfe8e2cff7a2"

    suspend fun getForecastByLocation(latitude: Double, longitude: Double): ForecastResponse? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.api.getForecastByCoordinates(latitude, longitude, API_KEY)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
