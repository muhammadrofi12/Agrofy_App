package com.example.agrofy_app.data

import com.example.agrofy_app.R
import com.example.agrofy_app.models.ForumPost

object DummyDataForum {
    private val dummyComments = listOf(
        com.example.agrofy_app.models.Comment(
            id = "c1",
            userName = "Budi",
            message = "Dari pengalaman saya, jerami sangat baik untuk pakan ternak sapi. Namun perlu diolah dulu dengan benar.",
            likes = 15,
            replies = listOf(
                com.example.agrofy_app.models.Comment(
                    id = "c1r1",
                    userName = "Ani",
                    message = "Betul, saya juga sudah mencoba. Bisa jelaskan lebih detail cara pengolahannya?",
                    likes = 8
                )
            )
        ),
        com.example.agrofy_app.models.Comment(
            id = "c2",
            userName = "Dewi",
            message = "Jerami bisa jadi alternatif pakan yang ekonomis, tapi harus diperhatikan nutrisinya.",
            likes = 10
        ),
        com.example.agrofy_app.models.Comment(
            id = "c3",
            userName = "Rudi",
            message = "Sebelum diberikan ke ternak, sebaiknya jerami direndam dulu dengan air kapur untuk mengurangi zat antinutrisi.",
            likes = 12
        )
    )

    val forumPosts = listOf(
        ForumPost(
            id = "1",
            authorName = "Fitri",
            question = "Ada yang punya tips membuat pupuk organik dari sisa panen padi? Apa saja langkah yang harus dilakukan?",
            likesCount = 137,
            commentsCount = 60,
            imageResource = null,
            comments = listOf(
                com.example.agrofy_app.models.Comment(
                    id = "c4",
                    userName = "Ahmad",
                    message = "Bisa dibuat kompos dengan mencampurkan dengan kotoran ternak",
                    likes = 5
                ),
                com.example.agrofy_app.models.Comment(
                    id = "c5",
                    userName = "Siti",
                    message = "Tambahkan EM4 untuk mempercepat proses pengomposan",
                    likes = 3
                )
            )
        ),
        ForumPost(
            id = "2",
            authorName = "Rofiul",
            question = "Apakah ada disini yang pernah mencoba menggunakan limbah jerami sebagai pakan ternak? Apa efeknya bagi kesehatan hewan?",
            likesCount = 230,
            commentsCount = 120,
            imageResource = R.drawable.jerami,
            comments = dummyComments
        )
    )

    fun getForumPostById(postId: String): ForumPost? {
        return forumPosts.find { it.id == postId }
    }
}