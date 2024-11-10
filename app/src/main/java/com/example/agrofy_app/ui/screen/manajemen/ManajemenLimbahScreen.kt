package com.example.agrofy_app.ui.screen.manajemen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.ui.components.AddLimbahModal
import com.example.agrofy_app.ui.components.LimbahItem
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold18
import com.example.agrofy_app.ui.theme.PoppinsBold20


@Composable
fun ManajemenLimbahScreen(
    navController: NavController
) {
    val filteredLimbah = DummyData.limbahItem.filter {
        it.kategori.equals("Limbah", ignoreCase = true)
    }
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Limbah",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.navigate("manajemen") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = GreenPrimary,
                contentColor = Color.White,
                modifier = Modifier
                    .height(110.dp)
                    .width(60.dp)
                    .padding(bottom = 46.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_button),
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(38.dp)
                )
            }
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
                // Total
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(GreenPrimary, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .zIndex(2f)
                    ) {
                        Text(
                            text = "Total",
                            style = PoppinsBold20,
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .offset(x = (-20).dp)
                            .zIndex(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .background(GreenLight, shape = RoundedCornerShape(12.dp))
                                .border(width = 2.dp, color = GreenPrimary, shape = RoundedCornerShape(12.dp))
                                .padding(start = 28.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)

                        ) {
                            Text(
                                text = "100 kg",
                                style = PoppinsBold18,
                                color = Color.Black
                            )
                        }
                    }

                }

                // Limbah List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredLimbah) { limbah ->
                        LimbahItem(limbah, navController = navController)
                    }
                }
            }
        }

        AddLimbahModal(
            showDialog = showAddDialog,
            onDismiss = { showAddDialog = false },
            onSave = { nama, berat, tanggal, deskripsi, imageUri ->
                // Handle save action here
                // You can add the new limbah to your data source
                // Process the imageUri if needed
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ManajemenLimbahScreenPreview() {
    val navController = rememberNavController()
    ManajemenLimbahScreen(navController = navController)
}