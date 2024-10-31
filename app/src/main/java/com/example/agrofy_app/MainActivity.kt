package com.example.agrofy_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.ui.screens.OnboardingScreen
import com.example.agrofy_app.ui.screens.SplashScreen
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
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
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashScreen(navController = navController)
            }
            composable("onboarding") {
                OnboardingScreen(navController = navController)
            }
            composable("profile") {
                ProfileScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Agrofy_AppTheme {
        MainScreen()
    }
}
