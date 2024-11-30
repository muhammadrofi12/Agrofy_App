package com.example.agrofy_app.models.forum

import com.google.gson.annotations.SerializedName

data class ForumResponse(
    val msg: String,
    val data: List<ForumPostData>
)

data class ForumPostData(
    val id: Int,
    @SerializedName("user_id") val userId: Int,
    val caption: String,
    @SerializedName("gambar") val gambarPost: String,
    val disukai: Int?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("nama_lengkap") val namaLengkap: String,
    val foto: String?
)
