package com.example.agrofy_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium10
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.ui.theme.PoppinsMedium18
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold12
import com.example.agrofy_app.ui.theme.PoppinsSemiBold14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold18
import com.example.agrofy_app.ui.theme.PoppinsSemiBold34

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("agrofy@gmail.com") }
    var password by remember { mutableStateOf("agrofy2024") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Focus state for each TextField
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val isFocusedEmail = remember { mutableStateOf(false) }
    val isFocusedPassword = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE8F5E9)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text atas "Masuk"
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Masuk",
                    style = PoppinsSemiBold34,
                    color = Color.Black
                )
            }

            // Form Login
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f),
                horizontalAlignment = Alignment.Start
            ) {
                // Label Email
                Text(text = "Email", style = PoppinsSemiBold18, color = Color.Black)

                Spacer(modifier = Modifier.height(8.dp))

                // Email Input
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .border(
                            width = 1.dp,
                            color = if (isFocusedEmail.value) GreenPrimary else Color.Gray,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .focusRequester(focusRequesterEmail)
                        .onFocusChanged { focusState -> isFocusedEmail.value = focusState.isFocused }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Label Kata Sandi
                Text(text = "Kata Sandi", style = PoppinsSemiBold18, color = Color.Black)

                Spacer(modifier = Modifier.height(8.dp))

                // Password Input
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Image(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = if (passwordVisible) R.drawable.show_eye else R.drawable.ic_eye),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .border(
                            width = 1.dp,
                            color = if (isFocusedPassword.value) GreenPrimary else Color.Gray,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .focusRequester(focusRequesterPassword)
                        .onFocusChanged { focusState -> isFocusedPassword.value = focusState.isFocused }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lupa Kata Sandi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        "Lupa Kata Sandi?",
                        color = Color.Black,
                        style = PoppinsSemiBold12,
                        modifier = Modifier.clickable { /* Action Lupa Password */ }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Button Masuk
                Button(
                    onClick = { /* Action Login */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("MASUK", style = PoppinsMedium18, color = Color.White)
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Daftar Link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Belum memiliki akun? ", style = PoppinsRegular14)
                    Text(
                        "Daftar",
                        style = PoppinsSemiBold14,
                        color = GreenPrimary,
                        modifier = Modifier.clickable {
                            navController.navigate("register")
                        }
                    )
                }
            }

            // Section Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Divider and Text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
                    Text(
                        "Lanjutkan dengan Google",
                        style = PoppinsRegular14,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Button Login dengan Google
                Button(
                    onClick = { /* Handle Google Sign-In */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Masuk dengan Google", style = PoppinsMedium18, color = Color.Black)
                }
            }
        }
    }
}
