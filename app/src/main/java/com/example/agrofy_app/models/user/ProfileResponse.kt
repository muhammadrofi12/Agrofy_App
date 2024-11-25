package com.example.agrofy_app.models.user

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val msg: String,
    val data: UserProfile
)

data class UserProfile(
    val id: Int,
    @SerializedName("nama_lengkap") val namaLengkap: String,
    val email: String,
    val role: String,
    val foto: String?
)

