package com.example.agrofy_app.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // List of icons and their respective screen keys
        val navItems = listOf(
            "Home" to Icons.Default.Home,
            "Pesanan" to Icons.Default.ShoppingCart,
            "Hubungi" to Icons.Default.Call,
            "Pekerjaan" to Icons.Default.Build,
            "Profil" to Icons.Default.Person
        )

        navItems.forEach { (screen, icon) ->
            IconButton(onClick = { onItemSelected(screen) }) {
                Icon(
                    imageVector = icon,
                    contentDescription = screen,
                    tint = Color(0xFF007BFF),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(onItemSelected = {})
}
