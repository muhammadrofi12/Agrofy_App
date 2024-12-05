package com.example.agrofy_app.models.forum

data class Comment(
    val id: Int,
    val userName: String,
    val message: String?,
    val userProfileImage: String?,
    val createdAt: String
)