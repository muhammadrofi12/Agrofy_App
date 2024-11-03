package com.example.agrofy_app.ui.screen.manajemen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.theme.PoppinsRegular30
import com.example.agrofy_app.ui.theme.screen.ProfileScreen

@Composable
fun ManajemenScreen(modifier: Modifier = Modifier, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Bagian ForumScreen di atas
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Manajemen Screen",
                    style = PoppinsRegular30,
                    color = Color.Black
                )
            }
            // Navbar
            BottomNavigationBar(
                navController = navController,
                onItemSelected = { selectedItem ->
                    // Logika saat item navigasi dipilih
                    println("Item yang dipilih: $selectedItem")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManajemenScreenPreview() {
    val dummyNavController = rememberNavController()
    ManajemenScreen(navController = dummyNavController)
}
