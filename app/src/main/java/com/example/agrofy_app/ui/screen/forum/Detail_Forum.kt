package com.example.agrofy_app.ui.screen.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.layout.ContentScale

// Komponen untuk menampilkan detail forum
@Composable
fun DetailForum(
    authorName: String,
    question: String,
    likesCount: Int,
    commentsCount: Int,
    imageResource: Int? = null, // Gambar opsional, bisa null
    comments: List<Comment> // Daftar komentar ditambahkan
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White) // Menambahkan background putih
                .padding(16.dp) // Menjaga padding seperti sebelumnya
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_author), // Pastikan ada gambar ini
                    contentDescription = "Icon profil",
                    modifier = Modifier.size(40.dp).clip(CircleShape)
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
                        .height(150.dp) // Menentukan tinggi agar tampak landscape
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop, // Membuat gambar memenuhi area
                    alignment = Alignment.Center
                )
            }
            // Menampilkan teks pertanyaan setelah gambar
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = question,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Ikon "Love" dan "Comment" di bawah pertanyaan, sebelum komentar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_love_active),
                        contentDescription = "Love icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$likesCount",
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

            Spacer(modifier = Modifier.height(10.dp)) // Memberikan ruang antara ikon dan komentar

            // Menampilkan komentar-komentar di dalam kolom post
            CommentList(comments = comments)
        }
    }
}

// Komponen untuk menampilkan kolom postingan lengkap dengan komentar
@Composable
fun DetailForumScreen(navController: NavController) {
    // Daftar komentar dikelola di sini
    var comments by remember { mutableStateOf(
        listOf(
            Comment("Fitri", "Kalau digunakan sebagai pakan sapi, bagaimana hasilnya?", 12, listOf(
                Comment("Reza", "Saya rasa sapi sangat suka jerami, terutama setelah fermentasi.", 5)
            )),

            Comment("Willy Calvin", "Saya setuju, ini bagus untuk ternak.", 15),
        )
    ) }

    var newCommentText by remember { mutableStateOf("") } // Menyimpan input teks komentar baru

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Diskusi",
                img = R.drawable.ic_back_circle,
                isIconButtonEnabled = false,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 24.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                // Postingan
                DetailForum(
                    authorName = "Rofy",
                    question = "Apakah ada disini yang pernah mencoba menggunakan limbah jerami sebagai pakan ternak? Apa efeknya bagi kesehatan hewan?",
                    likesCount = 230,
                    commentsCount = comments.size,
                    imageResource = R.drawable.jerami, // Gambar ada
                    comments = comments // Daftar komentar yang sudah dikelola
                )

                Spacer(modifier = Modifier.height(16.dp)) // Memberikan ruang setelah forum utama

                // Kolom input komentar baru tetap ada di sini
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Gambar profil pengguna
                    Image(
                        painter = painterResource(R.drawable.ic_author),
                        contentDescription = "Profile Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    // Input teks komentar
                    TextField(
                        value = newCommentText,
                        onValueChange = { newCommentText = it },
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        placeholder = { Text("Tulis komentar...") },
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Tombol kirim dengan warna latar belakang putih dan teks hitam
                    Button(
                        onClick = {
                            if (newCommentText.isNotEmpty()) {
                                // Menambahkan komentar baru ke daftar
                                comments = comments + Comment(
                                    userName = "User", // Nama pengguna
                                    message = newCommentText, // Pesan komentar
                                    likes = 0 // Komentar baru dimulai dengan 0 likes
                                )
                                newCommentText = "" // Reset input setelah kirim
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White // Latar belakang tombol putih
                        )
                    ) {
                        Text("Kirim", color = Color.Black) // Warna teks tombol hitam
                    }
                }
            }
        }
    }
}

// Komponen untuk menampilkan daftar komentar
@Composable
fun CommentList(comments: List<Comment>) {
    LazyColumn {
        items(comments) { comment ->
            CommentItem(comment)
        }
    }
}

// Komponen untuk menampilkan setiap item komentar
@Composable
fun CommentItem(comment: Comment) {
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
                Text(text = "${comment.likes} suka", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))

                // Menampilkan teks "Reply"
                Text(
                    text = "Balas",
                    fontSize = 12.sp,
                    color = Color.Blue
                )
            }

            // Menambahkan ikon love di sudut kanan
            Icon(
                painter = painterResource(R.drawable.ic_love_active), // Ganti dengan ikon love yang sesuai
                contentDescription = "Love Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
        }

        // Menampilkan sub-komentar jika ada
        if (comment.replies.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            SubCommentList(subComments = comment.replies)
        }
    }
}

// Komponen untuk menampilkan sub-komentar
@Composable
fun SubCommentList(subComments: List<Comment>) {
    Column {
        subComments.forEach { subComment ->
            CommentItem(subComment)
        }
    }
}

data class Comment(
    val userName: String,
    val message: String,
    val likes: Int,
    val replies: List<Comment> = emptyList() // Untuk komentar berbalas
)

@Preview(showBackground = true)
@Composable
fun PreviewDetailForumScreen() {
    DetailForumScreen(navController = rememberNavController())
}