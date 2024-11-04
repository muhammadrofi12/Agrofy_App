package com.example.agrofy_app.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.agrofy_app.R
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.ui.theme.GreenPrimary

@Composable
fun PemberdayaanNavigationBar(
    navController: NavController,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Get current route
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
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
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