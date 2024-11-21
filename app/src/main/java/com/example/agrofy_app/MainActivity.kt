package com.example.agrofy_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agrofy_app.data.DummyData.videoPembelajaran
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.screen.HomeScreen
import com.example.agrofy_app.ui.screen.LoginScreen
import com.example.agrofy_app.ui.screen.OnboardingScreen
import com.example.agrofy_app.ui.screen.RegisterScreen
import com.example.agrofy_app.ui.screen.forum.AddForumScreen
import com.example.agrofy_app.ui.screen.forum.DetailForumScreen
import com.example.agrofy_app.ui.screen.forum.ForumScreen
import com.example.agrofy_app.ui.screen.manajemen.ManajemenHasilScreen
import com.example.agrofy_app.ui.screen.manajemen.ManajemenLimbahScreen
import com.example.agrofy_app.ui.screen.manajemen.ManajemenProgressScreen
import com.example.agrofy_app.ui.screen.manajemen.ManajemenRiwayatScreen
import com.example.agrofy_app.ui.screen.manajemen.ManajemenScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.ArtikelScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.DetailArtikelScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.DetailVideoScreen
import com.example.agrofy_app.ui.screen.pemberdayaan.VideoScreen
import com.example.agrofy_app.ui.screen.profil.ProfileScreen
import com.example.agrofy_app.ui.screens.SplashScreen
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme


data class NavigationItem(
    val title: String,
    val iconResId: Int,
    val route: String,
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
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
                ArtikelScreen(navController = navController)
            }
            composable("manajemen") {
                ManajemenScreen(navController = navController)
            }
            composable("forum") {
                ForumScreen(navController = navController)
            }
            composable("profil") {
                ProfileScreen(navController = navController)
            }

            // Detail untuk pemberdayaan
            composable("video_detail/{videoId}") { backStackEntry ->
                val videoId = backStackEntry.arguments?.getString("videoId")?.toIntOrNull()
                val video = videoPembelajaran.find { it.id == videoId }
                if (video != null) {
                    DetailVideoScreen(video = video, navController = navController)
                }
            }
            composable("artikel_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                DetailArtikelScreen(artikelId = id, navController = navController)
            }


            // Manajemen
            composable("limbah") {
                ManajemenLimbahScreen(navController = navController)
            }
            composable("progress") {
                ManajemenProgressScreen(navController = navController)
            }
            composable("riwayat") {
                ManajemenRiwayatScreen(navController = navController)
            }
            composable("hasil_olah") {
                ManajemenHasilScreen(navController = navController)
            }

            // Add Forum
            composable("add_forum") {
                AddForumScreen(navController = navController)
            }
            // Detail Forum
            composable(
                "detail_forum/{postId}",
                arguments = listOf(navArgument("postId") { type = NavType.StringType })
            ) { backStackEntry ->
                val postId = backStackEntry.arguments?.getString("postId")
                DetailForumScreen(navController, postId)
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
