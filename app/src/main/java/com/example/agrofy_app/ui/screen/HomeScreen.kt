package com.example.agrofy_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.components.CardArtikelItem
import com.example.agrofy_app.ui.components.VideoItem
import com.example.agrofy_app.ui.components.WeatherForecast
import com.example.agrofy_app.ui.theme.BrownLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium16
import com.example.agrofy_app.ui.theme.PoppinsMedium20
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular30
import com.example.agrofy_app.ui.theme.PoppinsSemiBold16
import com.example.agrofy_app.viewmodels.ArtikelViewModel
import com.example.agrofy_app.viewmodels.VideoViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val artikelViewModel: ArtikelViewModel = viewModel()
    val videoViewModel: VideoViewModel = viewModel()

    val artikels by artikelViewModel.artikels.collectAsState()
    val videos by videoViewModel.videos.collectAsState()
    val isLoadingArtikels by artikelViewModel.isLoading.collectAsState()
    val isLoadingVideos by videoViewModel.isLoading.collectAsState()

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
            // Header: Text Selamat Datang dan Statistik
            item {
                Column {
                    // Text Selamat Datang
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
                }
            }

            // Video Pembelajaran
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Video Pembelajaran",
                        style = PoppinsSemiBold16,
                    )

                    Text(
                        text = "Lihat Semua",
                        style = PoppinsRegular12,
                        color = Color(0xFF9CA3AF),
                        modifier = Modifier.clickable {
                            navController.navigate("video")
                        }
                    )
                }
            }

            if (isLoadingVideos) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = GreenPrimary
                        )
                    }
                }
            } else if (videos.isEmpty()) {
                item {
                    Text(
                        text = "Tidak ada video yang tersedia.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Gray)
                    )
                }
            } else {
                items(videos.take(3)) { video ->
                    VideoItem(
                        video = video,
                        navController = navController
                    )
                }
            }



            // Artikel Pembelajaran
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Artikel",
                        style = PoppinsSemiBold16,
                    )

                    Text(
                        text = "Lihat Semua",
                        style = PoppinsRegular12,
                        color = Color(0xFF9CA3AF),
                        modifier = Modifier.clickable {
                            navController.navigate("artikel")
                        }
                    )
                }
            }

            item {
                if (isLoadingArtikels) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = GreenPrimary
                        )
                    }
                } else if (artikels.isEmpty()) {
                    Text(
                        text = "Tidak ada artikel yang tersedia.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Gray)
                    )
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(artikels.take(6)) { article ->
                            CardArtikelItem(
                                article = article,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }

        // Navbar
        BottomNavigationBar(
            navController = navController,
            onItemSelected = { selectedItem ->
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
fun PreviewHomeScreen() {
    val dummyNavController = rememberNavController()
    HomeScreen(navController = dummyNavController)
}

