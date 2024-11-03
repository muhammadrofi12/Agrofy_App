package com.example.agrofy_app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.BrownLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BrownLight
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_agrofy),
                contentDescription = "Agrofy Logo",
                modifier = Modifier.size(220.dp)
            )
        }

        // Delay 2 detik sebelum pindah ke layar onboarding
        coroutineScope.launch {
            delay(2000)
            navController.navigate("onboarding")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPrev() {
    Agrofy_AppTheme {
        SplashScreenDummy()
    }
}

@Composable
fun SplashScreenDummy() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BrownLight
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_agrofy),
                contentDescription = "Agrofy Logo",
                modifier = Modifier.size(220.dp)
            )
        }
    }
}
