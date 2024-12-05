package com.example.agrofy_app.models.forum

import com.google.gson.annotations.SerializedName

data class ForumDetailResponse(
    val msg: String,
    val data: List<ForumCommentData>,
)

data class ForumCommentData(
    val id: Int,
    @SerializedName("user_id") val userId: Int,
    val balasan: String?,
    @SerializedName("komunitas_id") val komunitasId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("gambar") val gambarPost: String?,
    val caption: String,
    @SerializedName("nama_lengkap") val namaComment: String,
    val foto: String?,
    val disukai: Int?
)


