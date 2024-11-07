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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.data.DummyData.artikelPembelajaran
import com.example.agrofy_app.data.DummyData.videoPembelajaran
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.screen.LoginScreen
import com.example.agrofy_app.ui.screen.RegisterScreen
import com.example.agrofy_app.ui.screen.forum.ForumScreen
import com.example.agrofy_app.ui.screen.HomeScreen
import com.example.agrofy_app.ui.screens.OnboardingScreen
import com.example.agrofy_app.ui.screens.SplashScreen
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.screen.ProfileScreen
import com.example.agrofy_app.ui.screen.manajemen.ManajemenScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.ArtikelScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.DetailArtikelScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.DetailVideoScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.KomentarScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.VideoScreen


data class NavigationItem(
    val title: String,
    val iconResId: Int,
    val route: String
)


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

    // Ambil route yang sedang aktif
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in listOf("beranda", "manajemen", "forum", "profil")) {
                BottomNavigationBar(
                    navController = navController,
                    onItemSelected = {}
                )
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashScreen(navController = navController) }
            composable("onboarding") { OnboardingScreen(navController = navController) }
            composable("login") { LoginScreen(navController = navController) }
            composable("register") { RegisterScreen(navController = navController) }
            composable("beranda") {
                HomeScreen(navController = navController)
            }
            composable("video") {
                VideoScreen(navController = navController)
            }
            composable("artikel") {
                ArtikelScreen(navController = navController, modifier = Modifier.padding(innerPadding))
            }
            composable("manajemen") {
                ManajemenScreen(navController = navController)
            }
            composable("forum") {
                ForumScreen(navController = navController, modifier = Modifier.padding(innerPadding))
            }
            composable("profil") {
                ProfileScreen(navController = navController, modifier = Modifier.padding(innerPadding))
            }

            // Detail untuk pemberdayaan
            composable("video_detail/{videoId}") { backStackEntry ->
                val videoId = backStackEntry.arguments?.getString("videoId")?.toIntOrNull()
                val video = videoPembelajaran.find { it.id == videoId }
                if (video != null) {
                    DetailVideoScreen(video = video, navController = navController)
                }
            }
            composable("artikel_detail/{articleId}") { backStackEntry ->
                val articleId = backStackEntry.arguments?.getString("articleId")?.toIntOrNull()
                println("Navigating to artikel_detail with ID: $articleId")
                val article = artikelPembelajaran.find { it.id == articleId }
                if (article != null) {
                    DetailArtikelScreen(article = article, navController = navController)
                } else {
                    println("Article with ID $articleId not found")
                }
            }
            composable("komentar_artikel") {
                KomentarScreen(navController = navController, modifier = Modifier.padding(innerPadding))
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
