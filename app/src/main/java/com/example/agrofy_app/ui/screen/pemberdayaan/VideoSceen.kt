package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.ui.components.VideoItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.PemberdayaanNavigationBar
import com.example.agrofy_app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoScreen(
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
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Top Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .shadow(4.dp, RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navController.navigate("beranda")  },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back_circle),
                                contentDescription = "Back",
                                modifier = Modifier.size(48.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Video Pembelajaran",
                            style = PoppinsRegular20,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

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

                // Video List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    val filteredVideos = DummyData.videoPembelajaran.filter { video ->
                        (selectedCategory == "Semua" || video.kategori == selectedCategory) &&
                                (searchQuery.isEmpty() || video.judul.contains(searchQuery, ignoreCase = true))
                    }

                    items(filteredVideos) { video ->
                        VideoItem(video = video)
                    }
                }
            }
        }
    }
}