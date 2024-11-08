package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.agrofy_app.ui.components.TopAppBar
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.models.Articles
import com.example.agrofy_app.ui.components.PemberdayaanNavigationBar
import com.example.agrofy_app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtikelScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Semua") }
    val categories = listOf("Semua", "Padi", "Jagung", "Pisang")

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                                modifier = Modifier
                                    .size(24.dp)
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
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 4.dp)
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
                        .padding(top = 24.dp)
                ) {
                    val filteredArticles = DummyData.artikelPembelajaran.filter { article ->
                        (selectedCategory == "Semua" || article.kategori == selectedCategory) &&
                                (searchQuery.isEmpty() || article.judul.contains(searchQuery, ignoreCase = true))
                    }

                    items(filteredArticles) { article ->
                        ArticleCard(
                            article = article,
                            modifier = Modifier,
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


@Composable
fun ArticleCard(article: Articles, modifier: Modifier, onClick: () -> Unit) {
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
                Image(
                    painter = painterResource(id = article.photo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp))
                        .border(
                            width = 1.5.dp,
                            color = BrownPrimary,
                            shape = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp)
                        ),
                    contentScale = ContentScale.Crop
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
                        text = article.judul,
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
                    text = article.konten,
                    style = PoppinsRegular12,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}