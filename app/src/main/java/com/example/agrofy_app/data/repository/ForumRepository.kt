package com.example.agrofy_app.data.repository

import com.example.agrofy_app.data.api.forum.ForumApiService
import com.example.agrofy_app.data.api.forum.ForumRetrofitClient
import com.example.agrofy_app.models.Comment
import com.example.agrofy_app.models.ForumPost

class ForumRepository(
    private val apiService: ForumApiService = ForumRetrofitClient.instance
) {
    suspend fun getForumPosts(): Result<List<ForumPost>> {
        return try {
            val response = apiService.getForum()
            if (response.isSuccessful) {
                val forumPosts = response.body()?.data?.map { apiPost ->
                    ForumPost(
                        id = apiPost.id.toString(),
                        authorName = apiPost.namaLengkap,
                        question = extractTextFromHTML(apiPost.caption),
                        likesCount = apiPost.disukai ?: 0,
                        commentsCount = 0,
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



    suspend fun getForumComments(forumId: Int): Result<List<Comment>> {
        return try {
            val response = apiService.getForumComments(forumId)
            if (response.isSuccessful) {
                val comments = response.body()?.data?.map { apiComment ->
                    Comment(
                        userName = apiComment.namaLengkap,
                        message = extractTextFromHTML(apiComment.caption),
                        likes = apiComment.disukai ?: 0,
                        replies = emptyList() // TODO: Implement nested comments if supported by API
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

    // Helper function to extract plain text from HTML
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

