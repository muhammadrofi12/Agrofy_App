package com.example.agrofy_app.models

data class Comment(
    val id: String = "",
    val userName: String,
    val message: String,
    val likes: Int,
    val replies: List<Comment> = emptyList()
)
