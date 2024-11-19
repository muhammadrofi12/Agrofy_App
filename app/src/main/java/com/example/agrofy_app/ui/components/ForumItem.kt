package com.example.agrofy_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agrofy_app.R

@Composable
fun DetailForum(
    authorName: String,
    question: String,
    likesCount: Int,
    commentsCount: Int,
    imageResource: Int? = null, // Gambar opsional, bisa null
    comments: List<com.example.agrofy_app.models.Comment>,
) {
    var isLiked by remember { mutableStateOf(false) }
    var currentLikesCount by remember { mutableIntStateOf(likesCount) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_author),
                    contentDescription = "Icon profil",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = authorName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            // Menambahkan gambar persegi panjang vertikal jika imageResource tidak null
            imageResource?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(it),
                    contentDescription = "Image above question",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            }
            // Text pertanyaan
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = question,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Ikon like dan comment
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isLiked = !isLiked
                        currentLikesCount += if (isLiked) 1 else -1
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_love_active),
                        contentDescription = "Love icon",
                        modifier = Modifier.size(24.dp),
                        tint = if (isLiked) Color.Red else Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$currentLikesCount",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_comment),
                        contentDescription = "Comment icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$commentsCount",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            CommentList(comments = comments)
        }
    }
}

@Composable
fun CommentList(comments: List<com.example.agrofy_app.models.Comment>) {
    LazyColumn {
        items(comments) { comment ->
            CommentItem(comment)
        }
    }
}

@Composable
fun CommentItem(comment: com.example.agrofy_app.models.Comment) {
    var isLiked by remember { mutableStateOf(false) }
    var currentLikes by remember { mutableIntStateOf(comment.likes) }

    Column(modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_author),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = comment.userName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = comment.message, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // Row untuk likes dan reply sebagai teks
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menampilkan jumlah like
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "$currentLikes suka", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Balas",
                    fontSize = 12.sp,
                    color = Color.Blue
                )
            }

            // Menambahkan ikon love di sudut kanan
            Icon(
                painter = painterResource(R.drawable.ic_love_active),
                contentDescription = "Love Icon",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        isLiked = !isLiked
                        currentLikes += if (isLiked) 1 else -1
                    },
                tint = if (isLiked) Color.Red else Color.Gray
            )
        }

        // Menampilkan sub-komentar jika ada
        if (comment.replies.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            SubCommentList(subComments = comment.replies)
        }
    }
}

@Composable
fun SubCommentList(subComments: List<com.example.agrofy_app.models.Comment>) {
    Column {
        subComments.forEach { subComment ->
            CommentItem(subComment)
        }
    }
}

// Preview detailforum
@Preview(showBackground = true)
@Composable
fun PreviewDetailForum() {
    val dummyComments = listOf(
        com.example.agrofy_app.models.Comment(
            userName = "User1",
            message = "Komentar pertama",
            likes = 10,
            replies = emptyList()
        ),
        com.example.agrofy_app.models.Comment(
            userName = "User2",
            message = "Komentar kedua",
            likes = 5,
            replies = listOf(
                com.example.agrofy_app.models.Comment(
                    userName = "User3",
                    message = "Balasan untuk komentar kedua",
                    likes = 3,
                    replies = emptyList()
                )
            )
        )
    )

    DetailForum(
        authorName = "Rofi",
        question = "Apa solusi untuk masalah pengelolaan limbah di pertanian organik?",
        likesCount = 20,
        commentsCount = dummyComments.size,
        imageResource = R.drawable.jerami,
        comments = dummyComments
    )
}
