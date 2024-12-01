package com.example.agrofy_app.data.repository

import com.example.agrofy_app.data.api.forum.AddCommentRequest
import com.example.agrofy_app.data.api.forum.ForumApiService
import com.example.agrofy_app.data.api.forum.ForumRetrofitClient
import com.example.agrofy_app.models.forum.Comment
import com.example.agrofy_app.models.forum.ForumPost

class ForumRepository(
    private val apiService: ForumApiService = ForumRetrofitClient.instance,
) {
    suspend fun getForumPosts(): Result<List<ForumPost>> {
        return try {
            val response = apiService.getForum()
            if (response.isSuccessful) {
                val forumPosts = response.body()?.data?.map { apiPost ->
                    val commentsResponse = apiService.getForumPostAndComments(apiPost.id)
                    val commentsCount = if (commentsResponse.isSuccessful) {
                        commentsResponse.body()?.data?.size ?: 0 // Hitung jumlah komentar
                    } else {
                        0
                    }

                    ForumPost(
                        id = apiPost.id.toString(),
                        authorName = apiPost.namaLengkap,
                        question = extractTextFromHTML(apiPost.caption),
                        likesCount = apiPost.disukai ?: 0,
                        commentsCount = commentsCount, // Set jumlah komentar
                        imageResource = apiPost.gambarPost?.takeIf { it.isNotEmpty() },
                        authorProfileImage = apiPost.foto
                    )
                } ?: emptyList()
                Result.success(forumPosts)
            } else {
                Result.failure(Exception("Failed to fetch forum posts: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getForumPostDetail(forumId: Int): Result<ForumPost> {
        return try {
            val response = apiService.getForumPostAndComments(forumId)
            if (response.isSuccessful) {
                val apiData = response.body()?.data
                if (!apiData.isNullOrEmpty()) {
                    // Mengambil post pertama sebagai dasar
                    val apiPost = apiData.first()

                    Result.success(
                        ForumPost(
                            id = apiPost.id.toString(),
                            authorName = apiPost.namaComment,   // Nama lengkap
                            question = extractTextFromHTML(apiPost.caption),
                            likesCount = apiPost.disukai ?: 0,
                            commentsCount = apiData.size,      // Total jumlah balasan
                            imageResource = apiPost.gambarPost?.takeIf { it.isNotEmpty() },
                            authorProfileImage = apiPost.foto  // Foto profil comment
                        )
                    )
                } else {
                    Result.failure(Exception("Post not found"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getForumComments(forumId: Int): Result<List<Comment>> {
        return try {
            val response = apiService.getForumPostAndComments(forumId)
            if (response.isSuccessful) {
                val comments = response.body()?.data?.map { apiComment ->
                    Comment(
                        id = apiComment.id,
                        userName = apiComment.namaComment,
                        message = extractTextFromHTML(apiComment.balasan),
                        userProfileImage = apiComment.foto,
                        likes = apiComment.disukai ?: 0,
                        createdAt = apiComment.createdAt
                    )
                } ?: emptyList()
                Result.success(comments)
            } else {
                Result.failure(Exception("Failed to fetch comments: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addComment(forumId: Int, commentText: String): Result<Boolean> {
        return try {
            val response = apiService.addComment(forumId, AddCommentRequest(commentText))
            if (response.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(Exception("Failed to add comment: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Ekstrak HTML
    private fun extractTextFromHTML(htmlContent: String): String {
        return htmlContent
            .replace("<ol>", "")
            .replace("</ol>", "")
            .replace("<li>", "")
            .replace("</li>", " ")
            .replace("<p>", "")
            .replace("</p>", "")
            .trim()
    }
}