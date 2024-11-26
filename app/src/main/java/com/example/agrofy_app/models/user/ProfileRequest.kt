package com.example.agrofy_app.models.user

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("nama_lengkap") val namaLengkap: String,
    val email: String,
    val password: String = "",
    val confirmPassword: String = "",
    val foto: String? = null,
)
