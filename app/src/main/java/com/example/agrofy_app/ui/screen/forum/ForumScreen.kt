package com.example.agrofy_app.ui.screen.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.agrofy_app.ui.components.BottomNavigationBar
import androidx.compose.foundation.background

@Composable
fun ForumPost(
    authorName: String,
    question: String,
    likesCount: Int,
    commentsCount: Int,
    imageResource: Int? = null // Gambar opsional, bisa null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
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
                    painter = painterResource(it), // Menggunakan parameter imageResource yang berbeda untuk setiap post
                    contentDescription = "Image above question",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
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

            // Ikon "Love" dan "Comment" di kanan bawah
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
        }
    }
}

@Composable
fun ForumScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        topBar = {

            com.example.agrofy_app.ui.components.TopAppBar(
                navController = navController,
                title = "Forum Diskusi",
                img = R.drawable.ic_forum
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Tindakan saat tombol tambah diklik */ },
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = (-40).dp) // Menggeser ke atas
            ) {
                Icon(
                    painter = painterResource(R.drawable.tambah),
                    contentDescription = "tambah",
                    modifier = Modifier.size(15.dp), // Ukuran ikon
                    tint = Color.Black
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Postingan Forum pertama
                ForumPost(
                    authorName = "Fitri",
                    question = "Ada yang punya tips membuat pupuk organik dari sisa panen padi? Apa saja langkah yang harus dilakukan?",

                    likesCount = 137,
                    commentsCount = 60,
                    imageResource = null // Tidak ada gambar
                )

                // Postingan Forum kedua
                ForumPost(
                    authorName = "Rofy",
                    question = "Apakah ada disini yang pernah mencoba menggunakan limbah jerami sebagai pakan ternak? Apa efeknya bagi kesehatan hewan?",
                    likesCount = 230,
                    commentsCount = 120,
                    imageResource = R.drawable.jerami // Gambar ada
                )
            }

            // BottomNavigationBar
            BottomNavigationBar(
                navController = navController,
                onItemSelected = { /* Implementasi aksi */ },
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Fix for 'align' error
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForumScreenPreview() {
    val dummyNavController = rememberNavController()
    ForumScreen(navController = dummyNavController)
}
