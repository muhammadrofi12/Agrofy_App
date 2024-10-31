package com.example.agrofy_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.agrofy_app.R
import com.google.accompanist.pager.*
import com.example.agrofy_app.ui.theme.BrownLight

@Composable
fun OnboardingScreen(navController: NavController) {
    // State untuk mengelola halaman pager
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BrownLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Mengatur halaman onboarding menggunakan HorizontalPager
            HorizontalPager(state = pagerState, count = 3, modifier = Modifier.weight(1f)) { page ->
                when (page) {
                    0 -> OnboardingPage(
                        imageRes = R.drawable.onboarding_1,
                        title = "Selamat Datang di Agrofy",
                        description = "Mulai aksi kecilmu sekarang! Edukasi diri tentang pengolahan limbah dan buat perubahan nyata"
                    )
                    1 -> OnboardingPage(
                        imageRes = R.drawable.onboarding_2,
                        title = "Ubah Limbah Menjadi Nilai",
                        description = "Ambil tindakan untuk masa depan lebih hijau dengan manajemen limbah yang cerdas"
                    )
                    2 -> OnboardingPage(
                        imageRes = R.drawable.onboarding_3,
                        title = "Maksimalkan Potensi Limbah Pertanianmu",
                        description = "Dengan langkah tepat, limbah bukan lagi masalah. Pelajari caranya di sini"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol "LANJUT" atau "MASUK/DAFTAR" di halaman terakhir
            if (pagerState.currentPage < 2) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1) // Geser ke halaman berikutnya
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("LANJUT")
                }

                TextButton(onClick = { navController.navigate("profile") }) {
                    Text("Lewati")
                }
            } else {
                // Di halaman terakhir, menampilkan tombol "MASUK" dan "DAFTAR"
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { /* route daftar */ },
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("DAFTAR")
                    }
                    Button(
                        onClick = { /* route login*/ },
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("MASUK")
                    }
                }
            }
        }
    }
    }



@Composable
fun OnboardingPage(imageRes: Int, title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Onboarding Image",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
