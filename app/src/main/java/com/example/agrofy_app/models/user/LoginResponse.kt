package com.example.agrofy_app.models.user

data class LoginResponse(
    val token: String,
    val msg: String,
    val role: String,
    val email: String,
    val name: String,
    val foto: String?
)
