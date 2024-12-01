package com.example.agrofy_app.models.forum

data class ForumPost(
    val id: String,
    val authorName: String,
    val question: String,
    val likesCount: Int,
    val commentsCount: Int,
    val imageResource: String? = null,
    val authorProfileImage: String? = null
)
