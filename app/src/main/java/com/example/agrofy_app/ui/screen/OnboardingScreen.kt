@file:Suppress("DEPRECATION")

package com.example.agrofy_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.BrownLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold26
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.ui.theme.PoppinsMedium18
import com.example.agrofy_app.ui.theme.PoppinsRegular16
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

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

            // Menampilkan tombol "Lanjut" atau "Lewati"
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

                TextButton(onClick = { navController.navigate("login") }) {
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
