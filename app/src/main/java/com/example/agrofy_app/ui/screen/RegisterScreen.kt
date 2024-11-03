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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium18
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold18
import com.example.agrofy_app.ui.theme.PoppinsSemiBold34

@Composable
fun RegisterScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("Agrofy") }
    var lastName by remember { mutableStateOf("Jaya") }
    var email by remember { mutableStateOf("agrofy@gmail.com") }
    var password by remember { mutableStateOf("agrofy") }
    var confirmPassword by remember { mutableStateOf("agrofy") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Focus state for each TextField
    val focusRequesterFirstName = remember { FocusRequester() }
    val focusRequesterLastName = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterConfirmPassword = remember { FocusRequester() }

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
            // Img Back Arrow
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.back_vector),
                        contentDescription = "Back"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Text atas "Registrasi"
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Registrasi",
                    style = PoppinsSemiBold34,
                    color = Color.Black
                )
            }

            // Form Register
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f),
                horizontalAlignment = Alignment.Start
            ) {
                // Input Nama Depan dan Nama Belakang dalam satu Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Nama Depan", style = PoppinsSemiBold18, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(6.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                                .focusRequester(focusRequesterFirstName)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text("Nama Belakang", style = PoppinsSemiBold18, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(6.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                                .focusRequester(focusRequesterLastName)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Input Email
                Text("Email", style = PoppinsSemiBold18, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                        .focusRequester(focusRequesterEmail)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input Kata Sandi
                Text("Kata Sandi", style = PoppinsSemiBold18, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
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
                        .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                        .focusRequester(focusRequesterPassword)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input Konfirmasi Kata Sandi
                Text("Konfirmasi Kata Sandi", style = PoppinsSemiBold18, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    singleLine = true,
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Image(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = if (confirmPasswordVisible) R.drawable.show_eye else R.drawable.ic_eye),
                                contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                        .focusRequester(focusRequesterConfirmPassword)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button Daftar
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("DAFTAR", style = PoppinsMedium18, color = Color.White)
                }
            }

            // Footer Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
                    Text("Lanjutkan dengan Google", style = PoppinsRegular14, modifier = Modifier.padding(horizontal = 8.dp))
                    Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Action google */ },
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
                Spacer(modifier = Modifier.height(16.dp))

                // Sudah memiliki akun
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sudah memiliki akun?", style = PoppinsRegular14, color = Color.Black)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Masuk",
                        style = PoppinsSemiBold14,
                        color = GreenPrimary,
                        modifier = Modifier.clickable { navController.navigate("login") }
                    )
                }
            }
        }
    }
}
