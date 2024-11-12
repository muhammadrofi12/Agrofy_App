package com.example.agrofy_app.ui.screen.manajemen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.models.HasilOlah
import com.example.agrofy_app.ui.components.AddHasilOlahModal
import com.example.agrofy_app.ui.components.HasilOlahCard
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary

@Composable
fun ManajemenHasilScreen(navController: NavController) {
    var showAddOlahanDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Hasil Olahan",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.navigate("manajemen") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddOlahanDialog = true },
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
                    modifier = Modifier.size(38.dp)
                )
            }
        }
    ) { paddingValues ->
        HasilOlahGrid(paddingValues = paddingValues)
        AddHasilOlahModal(
            showDialog = showAddOlahanDialog,
            onDismiss = { showAddOlahanDialog = false },
            onSave = { nama, jumlah, deskripsi, selectedImageUri ->
                // Handle save action here
                // You can add the new hasil olah to your data source
                // Process the selectedImageUri if needed
            }
        )
    }
}

@Composable
fun HasilOlahGrid(paddingValues: PaddingValues) {
    val hasilOlahList = listOf(
        HasilOlah(R.drawable.atap_jerami, "Atap Jeramiii", "50 Buah", "deskripsi"),
        HasilOlah(R.drawable.atap_jerami, "Atap Jeramii","50 Buah", "deskripsi"),
        HasilOlah(R.drawable.atap_jerami, "Atap Jeramiiiii","50 Buah", "deskripsi"),
        HasilOlah(R.drawable.atap_jerami, "Atap Jerami","50 Buah", "deskripsi"),
        HasilOlah(R.drawable.atap_jerami, "Atap Jerami","50 Buah", "deskripsi"),
        HasilOlah(R.drawable.atap_jerami, "Atap Jerami","50 Buah", "deskripsi"),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        items(hasilOlahList) { hasilOlah ->
            HasilOlahCard(
                hasilOlah = hasilOlah,
                onLihatClick = { /* Handle lihat click */ },
                onHapusClick = { /* Handle hapus click */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManajemenHasilScreenPreview() {
    val dummyNavController = rememberNavController()
    ManajemenHasilScreen(navController = dummyNavController)
}