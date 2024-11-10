package com.example.agrofy_app.ui.components

import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.GreenPrimary

@Composable
fun PemberdayaanNavigationBar(
    navController: NavController,
    onItemSelected: (String) -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val activeColor = GreenPrimary
    val inactiveColor = Color.Black

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 8.dp
    ) {
        // Video Menu Item
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_video),
                    contentDescription = "Video",
                    tint = if (currentRoute == "video") activeColor else inactiveColor,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    "Video",
                    color = if (currentRoute == "video") activeColor else inactiveColor,
                    fontSize = 12.sp
                )
            },
            selected = currentRoute == "video",
            selectedContentColor = activeColor,
            unselectedContentColor = inactiveColor,
            onClick = {
                if (currentRoute != "video") {
                    onItemSelected("video")
                    navController.navigate("video") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )

        // Article Menu Item
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_artikel),
                    contentDescription = "Artikel",
                    tint = if (currentRoute == "artikel") activeColor else inactiveColor,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    "Artikel",
                    color = if (currentRoute == "artikel") activeColor else inactiveColor,
                    fontSize = 12.sp
                )
            },
            selected = currentRoute == "artikel",
            selectedContentColor = activeColor,
            unselectedContentColor = inactiveColor,
            onClick = {
                if (currentRoute != "artikel") {
                    onItemSelected("artikel")
                    navController.navigate("artikel") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PemberdayaanNavigationBarPreview() {
    val navController = rememberNavController()
    PemberdayaanNavigationBar(
        navController = navController,
        onItemSelected = {}
    )
}