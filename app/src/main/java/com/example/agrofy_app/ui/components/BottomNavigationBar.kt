package com.example.agrofy_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.NavigationItem
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium12
import com.example.agrofy_app.ui.theme.PoppinsRegular12

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf("Beranda") }

    // Cek kondisi aktif
    LaunchedEffect(navController.currentBackStackEntry) {
        val route = navController.currentBackStackEntry?.destination?.route
        selectedItem = when (route) {
            "beranda" -> "Beranda"
            "manajemen" -> "Manajemen"
            "forum" -> "Forum"
            "profil" -> "Profil"
            else -> selectedItem
        }
    }

    val navItems = listOf(
        NavigationItem("Beranda", R.drawable.ic_beranda, "beranda"),
        NavigationItem("Manajemen", R.drawable.ic_dashboard, "manajemen"),
        NavigationItem("Forum", R.drawable.ic_forum, "forum"),
        NavigationItem("Profil", R.drawable.ic_profil, "profil")
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                shadowElevation = 30f
                shape = RectangleShape
                clip = true
                alpha = 1.0f
            }
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint()
                    val frameworkPaint = paint.asFrameworkPaint()
                    frameworkPaint.color = android.graphics.Color.WHITE

                    // Blur effect
                    frameworkPaint.setShadowLayer(
                        30.dp.toPx(),
                        0f,
                        -4.dp.toPx(),
                        android.graphics.Color.argb(100, 0, 0, 0)
                    )

                    canvas.drawRect(
                        0f,
                        -4.dp.toPx(),
                        size.width,
                        size.height,
                        paint
                    )
                }
            }
            .background(Color.White.copy(alpha = 0.95f))
//            .border(1.dp, Color.Gray.copy(alpha = 0.5f), RectangleShape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            navItems.forEach { item ->
                NavigationBarItem(
                    title = item.title,
                    iconResId = item.iconResId,
                    isSelected = selectedItem == item.title,
                    onClick = {
                        selectedItem = item.title
                        onItemSelected(item.title)
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun NavigationBarItem(
    title: String,
    iconResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            tint = if (isSelected) GreenPrimary else Color.Gray,
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            color = if (isSelected) GreenPrimary else Color.Gray,
            style = if (isSelected) PoppinsMedium12 else PoppinsRegular12,
            textAlign = TextAlign.Center
        )
    }
}

data class NavigationItem(
    val title: String,
    val iconResId: Int,
    val route: String,
)

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BottomNavigationBar(
        navController = navController,
        onItemSelected = {}
    )
}
