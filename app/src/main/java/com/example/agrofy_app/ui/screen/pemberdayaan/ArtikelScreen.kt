package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.models.pemberdayaan.ArtikelResponse
import com.example.agrofy_app.ui.components.PemberdayaanNavigationBar
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.BrownPrimary
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold14
import com.example.agrofy_app.ui.theme.Warning
import com.example.agrofy_app.viewmodels.pemberdayaan.ArtikelViewModel
import com.example.agrofy_app.viewmodels.pemberdayaan.KategoriViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtikelScreen(
    navController: NavController,
) {
    val artikelViewModel: ArtikelViewModel = viewModel()
    val kategoriViewModel: KategoriViewModel = viewModel()

    val artikels by artikelViewModel.artikels.collectAsState()
    val kategori by kategoriViewModel.kategori.collectAsState()
    val isLoading by artikelViewModel.isLoading.collectAsState()
    val error by artikelViewModel.error.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Semua") }

    // Tambahkan "Semua" ke daftar kategori
    val categories = remember(kategori) {
        listOf("Semua") + kategori.map { it.namaKategori }
    }

    // Efek untuk filter
    LaunchedEffect(searchQuery, selectedCategory) {
        artikelViewModel.filterArtikels(searchQuery, selectedCategory)
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
                title = "Artikel",
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
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = GreenActive
                    )
                }
                error != null -> {
                    Text(
                        text = "Error: $error",
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
                                        "Cari Artikel",
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
                                    onClick = { selectedCategory = category },
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

                        // Article List
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 12.dp)
                        ) {
                            items(artikels) { article ->
                                ArticleCard(
                                    article = article,
                                    onClick = {
                                        navController.navigate("artikel_detail/${article.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ArticleCard(
    article: ArtikelResponse,
    onClick: () -> Unit,
) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .border(
                width = 1.5.dp,
                color = GreenPrimary,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = GreenLight
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            // Img
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
            ) {
                AsyncImage(
                    model = "https://73zqc05b-3000.asse.devtunnels.ms/artikel/${article.thumbnail}",
                    contentDescription = article.judulArtikel,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp))
                        .border(
                            width = 1.5.dp,
                            color = BrownPrimary,
                            shape = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp)
                        ),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_image)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                // Judul
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = article.judulArtikel,
                        style = PoppinsMedium14,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = { isBookmarked = !isBookmarked },
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isBookmarked) R.drawable.bookmark_bold
                                else R.drawable.bookmark
                            ),
                            contentDescription = "Bookmark",
                            tint = Warning
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.deskripsi.replace("<[^>]*>".toRegex(), ""),
                    style = PoppinsRegular12,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}