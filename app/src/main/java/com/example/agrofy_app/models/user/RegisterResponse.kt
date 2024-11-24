package com.example.agrofy_app.models.user

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data: UserData? = null
)

data class UserData(
    val id: String,
    val name: String,
    val email: String
)
