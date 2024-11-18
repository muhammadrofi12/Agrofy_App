package com.example.agrofy_app.ui.components

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.agrofy_app.data.repository.WeatherRepository
import com.example.agrofy_app.models.Weather
import com.example.agrofy_app.models.WeatherData
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold10
import com.example.agrofy_app.ui.theme.PoppinsRegular10
import com.example.agrofy_app.ui.theme.PoppinsRegular18
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@SuppressLint("MissingPermission")
@Composable
fun GetUserLocation(onLocationFetched: (latitude: Double, longitude: Double) -> Unit) {
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    LaunchedEffect(Unit) {
        try {
            val locationResult = fusedLocationClient.lastLocation
            locationResult.addOnSuccessListener { location: Location? ->
                location?.let {
                    onLocationFetched(it.latitude, it.longitude)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun WeatherData.toWeather(): Weather {
    val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(dt * 1000))
    val temperature = main.temp.toInt()
    val iconCode = weather.firstOrNull()?.icon ?: "01d"
    val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"

    return Weather(time, temperature, iconUrl)
}

@Composable
fun WeatherForecast(modifier: Modifier = Modifier) {
    val forecastData = remember { mutableStateOf<List<Weather>?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    // location by nama
    val locationName = remember { mutableStateOf<String?>(null) }

    // location by koordinat
    val latitude = remember { mutableStateOf<Double?>(null) }
    val longitude = remember { mutableStateOf<Double?>(null) }

    GetUserLocation { lat, lon ->
        latitude.value = lat
        longitude.value = lon
    }

    LaunchedEffect(latitude.value, longitude.value) {
        if (latitude.value != null && longitude.value != null) {
            isLoading.value = true
            try {
                val response = WeatherRepository.getForecastByLocation(latitude.value!!, longitude.value!!)
                response?.let { forecast ->
                    val weatherList = forecast.list.map { it.toWeather() }
                    forecastData.value = weatherList
                    // Mengambil nama dengan titik koordinat
                    locationName.value = "${forecast.city.name}, ${forecast.city.country}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoading.value = false
        }
    }

    val currentDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(Date())

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0f))
    ) {
        Box {
            Column {
                Text(
                    text = "Cuaca hari ini - ${locationName.value ?: "Mencari lokasi..."}",
                    style = PoppinsRegular18,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Tanggal: $currentDate",
                    style = PoppinsRegular10,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (isLoading.value) {
                    Text(text = "Memuat data...", style = PoppinsBold10, color = Color.Gray)
                } else {
                    forecastData.value?.let { weatherList ->
                        val activeIndex = weatherList.indexOfFirst {
                            it.time.substringBefore(":").toIntOrNull() == currentHour
                        }

                        val startIndex = (activeIndex - 3).coerceAtLeast(0)
                        val endIndex = (startIndex + 6).coerceAtMost(weatherList.size - 1)
                        val displayWeather = weatherList.subList(startIndex, endIndex + 1)

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            displayWeather.forEachIndexed { index, weather ->
                                val isActive = (index == 3)
                                WeatherItem(weather = weather, isActive = isActive)
                            }
                        }
                    } ?: Text(text = "Data tidak ditemukan", style = PoppinsBold10, color = Color.Red)
                }
            }
        }
    }
}

@Composable
private fun WeatherItem(weather: Weather, isActive: Boolean) {
    val backgroundColor = if (isActive) GreenPrimary else GreenLight
    val iconActive = if (isActive) Color.White else Color.Black

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(2.dp)
            .width(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.time,
            style = PoppinsBold10,
            color = iconActive,
        )
        AsyncImage(
            model = weather.icon,
            contentDescription = null,
            modifier = Modifier.size(26.dp),
            colorFilter = if (isActive) {
                ColorFilter.tint(Color.White)
            } else {
                ColorFilter.tint(Color.Black)
            },
            contentScale = ContentScale.Fit
        )
        Text(
            text = "${weather.temperature}°",
            style = PoppinsBold10,
            color = iconActive,
        )
    }
}