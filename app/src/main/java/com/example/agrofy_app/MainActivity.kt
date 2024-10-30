package com.example.agrofy_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.components.BottomNavigationBar
import com.example.agrofy_app.ui.theme.screen.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Agrofy_AppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(onItemSelected = { selectedItem ->
                // Logika saat item navigasi dipilih
                println("Item yang dipilih: $selectedItem")
            })
        }
    ) { innerPadding ->
        ProfileScreen(modifier = Modifier.padding(innerPadding)) // Menampilkan HomeScreen
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Agrofy_AppTheme {
        MainScreen()
    }
}
