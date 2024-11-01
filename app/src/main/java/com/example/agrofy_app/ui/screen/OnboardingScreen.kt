package com.example.agrofy_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.agrofy_app.R
import com.google.accompanist.pager.*
import com.example.agrofy_app.ui.theme.BrownLight
import com.example.agrofy_app.ui.theme.PoppinsBold26
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular16
import androidx.compose.ui.graphics.Color
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium10
import com.example.agrofy_app.ui.theme.PoppinsMedium12
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.ui.theme.PoppinsMedium18

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
                        description = "Mulai aksi kecilmu sekarang! Edukasi diri tentang pengolahan limbah dan buat perubahan nyata",
                        pagerState = pagerState
                    )
                    1 -> OnboardingPage(
                        imageRes = R.drawable.onboarding_2,
                        title = "Ubah Limbah Menjadi Nilai",
                        description = "Ambil tindakan untuk masa depan lebih hijau dengan manajemen limbah yang cerdas",
                        pagerState = pagerState
                    )
                    2 -> OnboardingPage(
                        imageRes = R.drawable.onboarding_3,
                        title = "Maksimalkan Potensi Limbah Pertanianmu",
                        description = "Dengan langkah tepat, limbah bukan lagi masalah. Pelajari caranya di sini",
                        pagerState = pagerState
                    )
                }
            }

            Spacer(modifier = Modifier
                .height(16.dp)
            )

            // Menampilkan tombol "Lanjut" atau "Lewati" pada halaman selain halaman terakhir
            if (pagerState.currentPage < 2) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp, start = 60.dp, end = 60.dp),
                    shape = RoundedCornerShape(6),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("LANJUT",
                        style = PoppinsMedium18,)
                }

                TextButton(onClick = { navController.navigate("profile") }) {
                    Text("Lewati",
                        style = PoppinsMedium14,
                        color = Color.Black.copy(alpha = 0.8f))
                }
            } else {
                // Menampilkan tombol "MASUK" dan "DAFTAR" di halaman terakhir
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 42.dp)
                ) {
                    // Tombol DAFTAR
                    Button(
                        onClick = { navController.navigate("register") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(6)
                            ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(6),
                    ) {
                        Text(
                            "DAFTAR",
                            color = Color.Black,
                            style = PoppinsMedium18
                        )
                    }

                    // Tombol MASUK
                    Button(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = GreenPrimary,
                                shape = RoundedCornerShape(6)
                            ),
                        shape = RoundedCornerShape(6),
                        colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary)
                    ) {
                        Text(
                            "MASUK",
                            color = Color.White,
                            style = PoppinsMedium18
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingPage(imageRes: Int, title: String, description: String, pagerState: PagerState) {
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
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = title,
            style = PoppinsBold26,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = PoppinsRegular16,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        // Indicator titik
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = Color.Gray
        )
    }
}
