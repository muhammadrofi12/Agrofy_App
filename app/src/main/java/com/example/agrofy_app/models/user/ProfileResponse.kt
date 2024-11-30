package com.example.agrofy_app.models.user

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val msg: String,
    val data: JsonElement
)

data class UserProfile(
    val id: Int,
    @SerializedName("nama_lengkap") val namaLengkap: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
    val role: String,
    val foto: String?
)

