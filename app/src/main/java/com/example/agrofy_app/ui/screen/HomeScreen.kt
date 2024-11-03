package com.example.agrofy_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.models.Weather
import com.example.agrofy_app.ui.components.CardArtikelItem
import com.example.agrofy_app.ui.components.VideoItem
import com.example.agrofy_app.ui.components.BottomNavigationBar
import java.util.Calendar
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.ui.theme.BrownLight
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold10
import com.example.agrofy_app.ui.theme.PoppinsMedium16
import com.example.agrofy_app.ui.theme.PoppinsMedium20
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular18
import com.example.agrofy_app.ui.theme.PoppinsRegular30
import com.example.agrofy_app.ui.theme.PoppinsSemiBold16


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
        ) {
            item {
                Column {
                    // Text Selamat datang
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profil),
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = "Selamat Datang Rofiul!",
                                style = PoppinsMedium20
                            )
                        }
                        IconButton(onClick = { /* Action notification */ }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                    }

                    // Card Statistik & Cuaca
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = BrownLight)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // Statistik
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                StatItem("Limbah", "100", R.drawable.limbah)
                                StatItem("Progres", "50", R.drawable.progress)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                StatItem("Selesai", "27", R.drawable.selesai)
                                StatItem("Olahan", "30", R.drawable.olahan)
                            }

                            // Garis Pembatas
                            Divider(
                                color = Color.Gray,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )

                            // Cuaca
                            WeatherForecast()
                        }
                    }
                }
            }

            // Video Pembelajaran
            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Video Pembelajaran",
                        style = PoppinsSemiBold16,
                    )

                    Text(
                        text = "Lihat Semua",
                        style = PoppinsRegular12,
                        color = Color(0xFF9CA3AF)
                    )
                }

            }

            items(DummyData.videoPembelajaran.take(2)) { video ->
                VideoItem(video = video)
            }

            // Artikel Pembelajaran
            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Artikel",
                        style = PoppinsSemiBold16,
                    )

                    Text(
                        text = "Lihat Semua",
                        style = PoppinsRegular12,
                        color = Color(0xFF9CA3AF)
                    )
                }

            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(DummyData.artikelPembelajaran) { article ->
                        CardArtikelItem(article = article)
                    }
                }
            }
        }

        // Navbar
        BottomNavigationBar(
            navController = navController,
            onItemSelected = { selectedItem ->
                // Logika saat item navigasi dipilih
                println("Item yang dipilih: $selectedItem")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}


@Composable
private fun StatItem(label: String, value: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = BrownLight
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Text Judul
            Text(
                text = label,
                style = PoppinsMedium16,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(bottom = 4.dp)
            )

            // Image and Value
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Box image
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(GreenPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(34.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Value Angka
                Text(
                    text = value,
                    style = PoppinsRegular30,
                    color = Color.Black
                )
            }
        }

    }
}

@Composable
private fun WeatherForecast() {
    // Mengambil waktu terkini (jam) dari sistem
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val weatherData = DummyData.weatherForecast

    // Cari indeks dari item yang aktif
    val activeIndex = weatherData.indexOfFirst {
        it.time.substringBefore(":").toIntOrNull() == currentHour
    }

    // Tentukan rentang indeks yang menampilkan 7 item dengan aktif di tengah (indeks ke-3 dari 7)
    val startIndex = (activeIndex - 3).coerceAtLeast(0)
    val endIndex = (startIndex + 6).coerceAtMost(weatherData.size - 1)
    val displayedWeather = weatherData.subList(startIndex, endIndex + 1)

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0f))
    ) {
        Column(
        ) {
            Text(
                text = "Cuaca hari ini",
                style = PoppinsRegular18,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                displayedWeather.forEachIndexed { index, weather ->
                    val isActive = (index == 3)  // Item di posisi ke-3 adalah aktif
                    WeatherItem(weather = weather, isActive = isActive)
                }
            }
        }
    }
}

@Composable
private fun WeatherItem(weather: Weather, isActive: Boolean) {
    // cek kondisi aktif
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
        Icon(
            painter = painterResource(id = weather.icon),
            tint = iconActive,
            contentDescription = null,
            modifier = Modifier.size(26.dp)
        )
        Text(
            text = "${weather.temperature}°",
            style = PoppinsBold10,
            color = iconActive,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val dummyNavController = rememberNavController()
    HomeScreen(navController = dummyNavController)
}

