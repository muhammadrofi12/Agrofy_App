package com.example.agrofy_app.data.repository

import com.example.agrofy_app.data.api.forum.AddCommentRequest
import com.example.agrofy_app.data.api.forum.ForumApiService
import com.example.agrofy_app.data.api.forum.ForumRetrofitClient
import com.example.agrofy_app.models.forum.Comment
import com.example.agrofy_app.models.forum.ForumPost

class ForumRepository(
    private val apiService: ForumApiService = ForumRetrofitClient.instance,
) {
    // Get data list forum
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


//    suspend fun getForumPostDetail(forumId: Int): Result<ForumPost> {
//        return try {
//            val response = apiService.getForumPostAndComments(forumId)
//            if (response.isSuccessful) {
//                val apiData = response.body()?.data
//                if (apiData != null && apiData.isNotEmpty()) {
//                    val apiPost = apiData.first()
//                    // Memastikan bahwa deskripsi (caption) ada
//                    val description = extractTextFromHTML(apiPost.caption.takeIf { !it.isNullOrEmpty() } ?: "Deskripsi tidak tersedia.")
//
//                    Result.success(
//                        ForumPost(
//                            id = apiPost.id.toString(),
//                            authorName = apiPost.namaComment,
//                            question = description, // Menampilkan deskripsi yang valid
//                            likesCount = apiPost.disukai ?: 0,
//                            commentsCount = apiData.size, // Menghitung komentar berdasarkan data yang ada
//                            imageResource = apiPost.gambarPost?.takeIf { it.isNotEmpty() },
//                            authorProfileImage = apiPost.foto
//                        )
//                    )
//                } else {
//                    // Jika apiData kosong, tampilkan postingan dengan deskripsi default
//                    Result.success(
//                        ForumPost(
//                            id = forumId.toString(),
//                            authorName = "Anonymous",
//                            question = "Postingan ini belum memiliki deskripsi.",
//                            likesCount = 0,
//                            commentsCount = 0,
//                            imageResource = null,
//                            authorProfileImage = null
//                        )
//                    )
//                }
//            } else {
//                Result.failure(Exception("Error: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }

    // Get data list komentar by forum
    suspend fun getForumComments(forumId: Int): Result<List<Comment>> {
        return try {
            val response = apiService.getForumPostAndComments(forumId)
            if (response.isSuccessful) {
                val comments = response.body()?.data?.map { apiComment ->
                    Comment(
                        id = apiComment.id,
                        userName = apiComment.namaComment,
                        message = apiComment.balasan?.let { extractTextFromHTML(it) },
                        userProfileImage = apiComment.foto,
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


    // Add komentar
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

    // Add forum


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