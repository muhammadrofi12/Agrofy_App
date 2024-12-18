package com.example.agrofy_app

//import com.example.agrofy_app.ui.screen.forum.DetailForumScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.screen.HomeScreen
import com.example.agrofy_app.ui.screen.LoginScreen
import com.example.agrofy_app.ui.screen.OnboardingScreen
import com.example.agrofy_app.ui.screen.RegisterScreen
import com.example.agrofy_app.ui.screen.SplashScreen
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
import com.example.agrofy_app.ui.screen.profil.EditProfileScreen
import com.example.agrofy_app.ui.screen.profil.ProfileScreen
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.viewmodels.user.LoginViewModel
import com.example.agrofy_app.viewmodels.user.ProfileViewModel


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
    val loginViewModel: LoginViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()

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
                ProfileScreen(
                    navController = navController,
                    loginViewModel = loginViewModel
                )
            }
            composable("edit_profil") {
                EditProfileScreen(
                    navController = navController,
                    profileViewModel = profileViewModel
                )
            }

            composable("video_detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
                DetailVideoScreen(videoId = id, navController = navController)
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
            composable("detail_forum/{postId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("postId")?.toIntOrNull() ?: 0
                DetailForumScreen(navController = navController, forumId = id)
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
