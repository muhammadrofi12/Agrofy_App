package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.PemberdayaanNavigationBar
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.components.VideoItem
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold14
import com.example.agrofy_app.viewmodels.pemberdayaan.KategoriViewModel
import com.example.agrofy_app.viewmodels.pemberdayaan.VideoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoScreen(
    navController: NavController
) {
    val videoViewModel: VideoViewModel = viewModel()
    val kategoriViewModel: KategoriViewModel = viewModel()

    val videos by videoViewModel.videos.collectAsState()
    val kategori by kategoriViewModel.kategori.collectAsState()
    val isLoading by videoViewModel.isLoading.collectAsState()
    val error by videoViewModel.error.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Semua") }

    // Tambahkan "Semua" ke daftar kategori
    //    val categories = listOf("Semua", "Padi", "Jagung", "Pisang")
    val categories = remember(kategori) {
        if (kategori.isNotEmpty()) {
            listOf("Semua") + kategori.map { it.namaKategori }.take(3)
        } else {
            listOf("Semua")
        }
    }

    // Efek untuk filter
    LaunchedEffect(searchQuery, selectedCategory) {
        videoViewModel.filterVideos(searchQuery, selectedCategory)
    }

    Scaffold(
        bottomBar = {
            PemberdayaanNavigationBar(
                navController = navController,
                onItemSelected = { route ->
                    navController.navigate(route)
                }
            )
        },
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Video Pembelajaran",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.navigate("beranda") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(top = 12.dp)
        ) {
            when {
                isLoading -> {
                    // Indikator loading
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = GreenActive
                    )
                }
                error != null -> {
                    // Pesan error
                    Text(
                        text = "Terjadi kesalahan: $error",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Search Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                placeholder = {
                                    Text(
                                        "Cari Pembelajaran",
                                        style = PoppinsRegular14,
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Search,
                                        contentDescription = "Search Icon",
                                        tint = Color.Black,
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp)
                                    .background(Color(0xFFF5F5F5), RoundedCornerShape(10.dp)),
                                shape = RoundedCornerShape(10.dp),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = GreenPrimary,
                                    focusedBorderColor = GreenActive,
                                    containerColor = GreenLight
                                ),
                                singleLine = true,
                                textStyle = PoppinsRegular14
                            )
                        }

                        // Category Text
                        Text(
                            text = "Kategori",
                            style = PoppinsSemiBold14,
                            modifier = Modifier.padding(start = 20.dp, top = 2.dp, bottom = 4.dp)
                        )

                        // Category Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            categories.forEach { category ->
                                val isSelected = category == selectedCategory
                                Button(
                                    onClick = {
                                        selectedCategory = category
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSelected) GreenActive else Color.White
                                    ),
                                    border = ButtonDefaults.outlinedButtonBorder.copy(
                                        brush = SolidColor(if (isSelected) Color.Transparent else GreenPrimary)
                                    ),
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(26.dp),
                                    contentPadding = PaddingValues(vertical = 2.dp)
                                ) {
                                    Text(
                                        text = category,
                                        color = if (isSelected) Color.White else Color.Black,
                                        style = PoppinsRegular14,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        // Video List
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 12.dp)
                        ) {
                            items(videos) { video ->
                                VideoItem(
                                    video = video,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
