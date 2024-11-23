package com.example.agrofy_app.models.user

data class ProfileResponse(
    val id: Int,
    val name: String,
    val email: String,
    val role: String,
    val foto: String?
)
