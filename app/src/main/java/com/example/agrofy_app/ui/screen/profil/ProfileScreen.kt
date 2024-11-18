package com.example.agrofy_app.ui.screen.profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.components.LogoutDialog
import com.example.agrofy_app.ui.components.ProfileMenuItem
import com.example.agrofy_app.ui.theme.BrownPrimary
import com.example.agrofy_app.ui.theme.Error
import com.example.agrofy_app.ui.theme.PoppinsBold24
import com.example.agrofy_app.ui.theme.PoppinsSemiBold12

@Composable
fun ProfileScreen(navController: NavController) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    LogoutDialog(
        showDialog = showLogoutDialog,
        onDismiss = { showLogoutDialog = false },
        onConfirm = { showLogoutDialog = false },
        navController = navController
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .zIndex(0f),
            contentScale = ContentScale.Crop
        )

        // Background putih
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 180.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp)
                )
                .zIndex(1f)
        )

        // Profile Picture Container
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 120.dp)
                .size(100.dp)
                .background(Color.White, CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .zIndex(2f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 228.dp)
                .zIndex(2f)
        ) {
            // Profile Info
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rofiul",
                    style = PoppinsBold24,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Handle Edit Profile */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFAA00)),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.height(24.dp)
                ) {
                    Text(
                        text = "Edit Profil",
                        color = Color.White,
                        style = PoppinsSemiBold12,

                    )
                }

                Spacer(modifier = Modifier.height(36.dp))
            }

            // Menu Items
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ProfileMenuItem(
                    text = "Disimpan",
                    iconRes = R.drawable.ic_archive_book,
                    backgroundColor = BrownPrimary,
                    borderColor = BrownPrimary
                )
                ProfileMenuItem(
                    text = "Pengaturan",
                    iconRes = R.drawable.ic_setting,
                    backgroundColor = BrownPrimary,
                    borderColor = BrownPrimary
                )
                ProfileMenuItem(
                    text = "Pusat Bantuan",
                    iconRes = R.drawable.ic_help,
                    backgroundColor = BrownPrimary,
                    borderColor = BrownPrimary
                )
                ProfileMenuItem(
                    text = "Tentang Aplikasi",
                    iconRes = R.drawable.ic_info,
                    backgroundColor = BrownPrimary,
                    borderColor = BrownPrimary
                )
                ProfileMenuItem(
                    text = "Keluar",
                    iconRes = R.drawable.ic_keluar,
                    backgroundColor = Error,
                    textColor = Error,
                    borderColor = Error,
                    onClick = { showLogoutDialog = true }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Navigation
            BottomNavigationBar(
                navController = navController,
                onItemSelected = { selectedItem ->
                    println("Selected: $selectedItem")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val dummyNavController = rememberNavController()
    ProfileScreen(navController = dummyNavController)
}
