package com.example.agrofy_app.ui.screen.manajemen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.components.LimbahCardItem
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.components.WeatherForecast
import com.example.agrofy_app.ui.theme.BrownLight
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium16
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular30
import com.example.agrofy_app.ui.theme.PoppinsSemiBold16

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
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
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
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
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
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem(
                                            "Jerami",
                                            "07 Nov 2024",
                                            R.drawable.jerami
                                        )
                                        LimbahCardItem(
                                            "Jerami",
                                            "07 Nov 2024",
                                            R.drawable.jerami
                                        )
                                        LimbahCardItem(
                                            "Jerami",
                                            "07 Nov 2024",
                                            R.drawable.jerami
                                        )
                                        LimbahCardItem(
                                            "Jerami",
                                            "07 Nov 2024",
                                            R.drawable.jerami
                                        )
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
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .padding(start = 4.dp)
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

//                                        Box(
//                                            modifier = Modifier
//                                                .background(Color.Transparent)
//                                                .border(
//                                                    width = 1.dp,
//                                                    color = GreenPrimary,
//                                                    shape = RoundedCornerShape(4.dp)
//                                                )
//                                                .padding(horizontal = 12.dp, vertical = 4.dp)
//                                        ) {
//                                            Text(
//                                                text = "100 kg",
//                                                style = PoppinsMedium16,
//                                                color = Color.Black
//                                            )
//                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
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
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .padding(start = 4.dp)
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
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
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
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .padding(start = 4.dp)
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
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Jerami",
                                            "12 Des 2024",
                                            R.drawable.jerami
                                        )
                                        LimbahCardItem(
                                            "Atap Jerami",
                                            "12 Des 2024",
                                            R.drawable.atap_jerami
                                        )
                                        LimbahCardItem(
                                            "Jerami",
                                            "12 Des 2024",
                                            R.drawable.jerami
                                        )
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
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .padding(start = 8.dp)
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

@Preview(showBackground = true)
@Composable
fun ManajemenScreenPreview() {
    val dummyNavController = rememberNavController()
    ManajemenScreen(navController = dummyNavController)
}
