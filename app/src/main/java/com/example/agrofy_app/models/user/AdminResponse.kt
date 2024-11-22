package com.example.agrofy_app.models.user

data class AdminResponse(
    val id: Int,
    val email: String,
    val nama_lengkap: String,
    val password: String,
    val foto: String?,
    val role: String,
    val created_at: String,
    val updated_at: String
)
