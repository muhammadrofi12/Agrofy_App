package com.example.agrofy_app.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R

import com.example.agrofy_app.ui.components.BottomNavigationBar


@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Profile img
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .offset(y = (-40).dp)
                .size(80.dp)
                .background(Color.White, shape = CircleShape)
                .border(2.dp, Color.LightGray, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nama & Edit Profile
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Nama",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            TextButton(onClick = { /* Handle Edit Profile */ }) {
                Text(
                    text = "Edit Profil",
                    color = Color(0xFF007BFF),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // List Menu
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ProfileMenuItem("Pesanan", Icons.Default.ShoppingCart)
            ProfileMenuItem("Disimpan", Icons.Default.Star)
            ProfileMenuItem("Pengaturan", Icons.Default.Settings)
            ProfileMenuItem("Pusat Bantuan", Icons.Default.Settings)
            ProfileMenuItem("Tentang Aplikasi", Icons.Default.Info)
            ProfileMenuItem("Keluar", Icons.Default.ExitToApp, Color.Red)
        }

        // Navbar
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(
            navController = navController,
            onItemSelected = { selectedItem ->
                // Logika saat item navigasi dipilih
                println("Item yang dipilih: $selectedItem")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ProfileMenuItem(text: String, icon: ImageVector, iconTint: Color = Color(0xFF6F6F6F)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val dummyNavController = rememberNavController()
    ProfileScreen(navController = dummyNavController)
}

