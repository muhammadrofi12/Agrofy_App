package com.example.agrofy_app.models.user

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val token: String,
    val msg: String,
    val role: String,
    val email: String,
    @SerializedName("nama_lengkap") val namaLengkap: String,
    val foto: String?
)
