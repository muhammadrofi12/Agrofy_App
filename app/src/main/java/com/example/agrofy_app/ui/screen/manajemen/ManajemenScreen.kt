package com.example.agrofy_app.ui.screen.manajemen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.models.Weather
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.components.LimbahCardItem
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.BrownLight
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold10
import com.example.agrofy_app.ui.theme.PoppinsMedium16
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular18
import com.example.agrofy_app.ui.theme.PoppinsRegular30
import com.example.agrofy_app.ui.theme.PoppinsSemiBold16
import java.util.Calendar

@Composable
fun ManajemenScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Dashboard Limbah",
                img = R.drawable.ic_dashboard_manajemen,
                isIconButtonEnabled = false,
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp),
            ) {
                item {
                    Column {
                        Spacer(modifier = Modifier.height(24.dp))
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
                                    StatItem("Olahan", "130", R.drawable.olahan)
                                }

                                // Garis Pembatas
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp),
                                    thickness = 1.dp,
                                    color = Color.Gray
                                )

                                // Cuaca
                                WeatherForecast()
                            }
                        }

                        // Data Limbah
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenLight)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Limbah",
                                            style = PoppinsSemiBold16,
                                            color = Color.Black
                                        )

                                        Box(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .border(width = 1.dp, color = GreenPrimary, shape = RoundedCornerShape(4.dp))
                                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = "100 kg",
                                                style = PoppinsMedium16,
                                                color = Color.Black
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem("Jerami", "100", "07 Nov 2024", R.drawable.jerami)
                                        LimbahCardItem("Jerami", "100", "07 Nov 2024", R.drawable.jerami)
                                        LimbahCardItem("Jerami", "100", "07 Nov 2024", R.drawable.jerami)
                                        LimbahCardItem("Jerami", "100", "07 Nov 2024", R.drawable.jerami)
                                    }

                                    Button(
                                        onClick = { navController.navigate("limbah") },
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .width(200.dp)
                                            .height(42.dp)
                                            .padding(top = 10.dp),
                                        colors = ButtonDefaults.buttonColors(GreenPrimary),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "Lihat Selengkapnya",
                                                style = PoppinsRegular12,
                                                color = Color.White
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.back_vector_right),
                                                contentDescription = "Arrow",
                                                tint = Color.White,
                                                modifier = Modifier.size(24.dp).padding(start = 4.dp)
                                            )
                                        }
                                    }

                                }
                            }
                        }


                        // Progress Limbah
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenLight)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    // Title and weight section
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Progress",
                                            style = PoppinsSemiBold16,
                                            color = Color.Black
                                        )

                                        Box(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .border(width = 1.dp, color = GreenPrimary, shape = RoundedCornerShape(4.dp))
                                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = "100 kg",
                                                style = PoppinsMedium16,
                                                color = Color.Black
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                    }

                                    Button(
                                        onClick = { navController.navigate("progress") },
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .width(200.dp)
                                            .height(42.dp)
                                            .padding(top = 10.dp),
                                        colors = ButtonDefaults.buttonColors(GreenPrimary),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "Lihat Selengkapnya",
                                                style = PoppinsRegular12,
                                                color = Color.White
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.back_vector_right),
                                                contentDescription = "Arrow",
                                                tint = Color.White,
                                                modifier = Modifier.size(24.dp).padding(start = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Olahan Limbah
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenLight)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    // Title and weight section
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Hasil Olahan",
                                            style = PoppinsSemiBold16,
                                            color = Color.Black
                                        )

                                        Box(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .border(width = 1.dp, color = GreenPrimary, shape = RoundedCornerShape(4.dp))
                                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = "60 kg",
                                                style = PoppinsMedium16,
                                                color = Color.Black
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                    }

                                    Button(
                                        onClick = { navController.navigate("hasil_olah") },
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .width(200.dp)
                                            .height(42.dp)
                                            .padding(top = 10.dp),
                                        colors = ButtonDefaults.buttonColors(GreenPrimary),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "Lihat Selengkapnya",
                                                style = PoppinsRegular12,
                                                color = Color.White
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.back_vector_right),
                                                contentDescription = "Arrow",
                                                tint = Color.White,
                                                modifier = Modifier.size(24.dp).padding(start = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Riwayat Limbah
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenLight)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    // Title and weight section
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Riwayat",
                                            style = PoppinsSemiBold16,
                                            color = Color.Black
                                        )

                                        Box(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .border(width = 1.dp, color = GreenPrimary, shape = RoundedCornerShape(4.dp))
                                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                text = "60 kg",
                                                style = PoppinsMedium16,
                                                color = Color.Black
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Jerami", "100", "12 Des 2024", R.drawable.jerami)
                                        LimbahCardItem("Atap Jerami", "100", "12 Des 2024", R.drawable.atap_jerami)
                                        LimbahCardItem("Jerami", "100", "12 Des 2024", R.drawable.jerami)
                                    }

                                    Button(
                                        onClick = { navController.navigate("riwayat") },
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .width(200.dp)
                                            .height(42.dp)
                                            .padding(top = 10.dp),
                                        colors = ButtonDefaults.buttonColors(GreenPrimary),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Lihat Selengkapnya",
                                                style = PoppinsRegular12,
                                                color = Color.White
                                            )
                                            Icon(
                                                painter = painterResource(id = R.drawable.back_vector_right),
                                                contentDescription = "Arrow",
                                                tint = Color.White,
                                                modifier = Modifier.size(24.dp).padding(start = 8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(64.dp))
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text Judul
            Text(
                text = label,
                style = PoppinsMedium16,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Icon & Angka
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon
                Column(
                    modifier = Modifier.width(70.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(2.dp))
                // Angka
                Column(
                    modifier = Modifier.width(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = value,
                        style = PoppinsRegular30,
                        color = Color.Black
                    )
                }
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
        Box {
            Column {
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
                        val isActive = (index == 3)
                        WeatherItem(weather = weather, isActive = isActive)
                    }
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
fun ManajemenScreenPreview() {
    val dummyNavController = rememberNavController()
    ManajemenScreen(navController = dummyNavController)
}
