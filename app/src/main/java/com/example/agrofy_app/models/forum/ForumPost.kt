package com.example.agrofy_app.models.forum

data class ForumPost(
    val id: String,
    val authorName: String,
    val question: String,
    val likesCount: Int = 0,
    val commentsCount: Int? = null,
    val imageResource: String? = null,
    val authorProfileImage: String? = null,
    val created: String
)
