package com.example.agrofy_app.ui.screen

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agrofy_app.ui.theme.Error
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium18
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold12
import com.example.agrofy_app.ui.theme.PoppinsSemiBold14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold18
import com.example.agrofy_app.ui.theme.PoppinsSemiBold34
import com.example.agrofy_app.viewmodels.user.LoginUiState
import com.example.agrofy_app.viewmodels.user.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    var email by remember { mutableStateOf("rofi12@gmail.com") }
    var password by remember { mutableStateOf("rofi123") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Collect login state
    val loginState by viewModel.loginState.collectAsState()

    // Handle login state changes
    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginUiState.Loading -> {
                isLoading = true
                errorMessage = null
            }
            is LoginUiState.Success -> {
                isLoading = false
                errorMessage = null
                navController.navigate("beranda") {
                    popUpTo("login") { inclusive = true }
                }
            }
            is LoginUiState.Error -> {
                isLoading = false
                errorMessage = (loginState as LoginUiState.Error).message
            }
            LoginUiState.Initial -> {
                isLoading = false
                errorMessage = null
            }
        }
    }

    // Focus state for each TextField
    val focusRequesterEmail = remember { FocusRequester() }
    val focusRequesterPassword = remember { FocusRequester() }
    val isFocusedEmail = remember { mutableStateOf(false) }
    val isFocusedPassword = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background blur (same as before)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = createRadialGradientBrush(
                    center = Offset(1500f, 500f), radius = 1200f)
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = createRadialGradientBrush(
                    center = Offset(0f, 2000f), radius = 800f)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title Section
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

                /// Error message
                errorMessage?.let {
                    Text(
                        text = it,
                        color = Error,
                        style = PoppinsRegular14,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Login Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f),
                horizontalAlignment = Alignment.Start
            ) {
                // Email Field
                Text(text = "Email", style = PoppinsSemiBold18, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
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

                // Password Field
                Text(text = "Kata Sandi", style = PoppinsSemiBold18, color = Color.Black)
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
                        .border(
                            width = 1.dp,
                            color = if (isFocusedPassword.value) GreenPrimary else Color.Gray,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .focusRequester(focusRequesterPassword)
                        .onFocusChanged { focusState -> isFocusedPassword.value = focusState.isFocused }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Forgot Password
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

                // Login Button
                Button(
                    onClick = {
                        viewModel.login(email, password)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(6.dp),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("MASUK", style = PoppinsMedium18, color = Color.White)
                    }
                }

                // Register Link
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
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

            // Footer Section (Google Sign In)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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


fun createRadialGradientBrush(center: Offset, radius: Float): Brush {
    return Brush.radialGradient(
        colors = listOf(
            Color(0xFFBEFFD1),
            Color.Transparent
        ),
        center = center,
        radius = radius
    )
}