package com.example.agrofy_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium18
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold18
import com.example.agrofy_app.ui.theme.PoppinsSemiBold34
import com.example.agrofy_app.viewmodels.user.RegisterState
import com.example.agrofy_app.viewmodels.user.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val registerState = viewModel.registerState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Focus state for each TextField
    val focusRequesterName = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterConfirmPassword = remember { FocusRequester() }

    // Handle registration state changes
    LaunchedEffect(registerState.value) {
        when (val state = registerState.value) {
            is RegisterState.Success -> {
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
                snackbarHostState.showSnackbar("Registration successful!")
            }
            is RegisterState.Error -> {
                snackbarHostState.showSnackbar(state.message)
            }
            else -> {}
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // background blur
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = createRadialGradientBrush(
                        center = Offset(1500f, 500f), radius = 1200f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = createRadialGradientBrush(
                        center = Offset(0f, 2000f), radius = 800f
                    )
                )
        )

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
                // Input Nama Lengkap
                Text("Nama Lengkap", style = PoppinsSemiBold18, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(6.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                        .focusRequester(focusRequesterName),
                    placeholder = { Text("Masukan Nama Lengkap") },
                )

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
                        .focusRequester(focusRequesterEmail),
                    placeholder = { Text("Masukan Email") }
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
                        .focusRequester(focusRequesterPassword),
                    placeholder = { Text("Masukan Kata Sandi") }
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
                        .focusRequester(focusRequesterConfirmPassword),
                    placeholder = { Text("Masukan Ulang Kata Sandi") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button Daftar
                Button(
                    onClick = {
                        viewModel.register(name, email, password, confirmPassword)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(6.dp),
                    enabled = registerState.value !is RegisterState.Loading
                ) {
                    if (registerState.value is RegisterState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text("DAFTAR", style = PoppinsMedium18, color = Color.White)
                    }
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
                    Text(
                        "Lanjutkan dengan Google",
                        style = PoppinsRegular14,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
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

        // loading
        if (registerState.value is RegisterState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(enabled = false) {}, // Disable interaction
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = GreenPrimary)
            }
        }

        // Add SnackbarHost
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}
