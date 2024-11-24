package com.example.agrofy_app.models.user

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

