package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Articles
import com.example.agrofy_app.ui.theme.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Scaffold
import com.example.agrofy_app.ui.components.TopAppBar

@Composable
fun DetailArtikelScreen(
    article: Articles,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Artikel",
                img = R.drawable.ic_back_circle
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp)
            ) {
                // Article Details (Title, Date, Author)
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))

                        // Title
                        Text(
                            text = article.judul,
                            style = PoppinsSemiBold20,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Date and Author
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Calendar
                            Image(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "Kalender",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = formatDate(article.tanggal),
                                style = PoppinsRegular14,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Image(
                                painter = painterResource(id = R.drawable.ic_circle),
                                contentDescription = "Circle",
                                modifier = Modifier.size(6.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            // Author
                            Image(
                                painter = painterResource(id = R.drawable.ic_author),
                                contentDescription = "Author",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = article.author,
                                style = PoppinsRegular14,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // Article Image
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = article.photo),
                            contentDescription = article.judul,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF8B4513),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }

                // Content
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        // Description
                        Text(
                            text = article.deskripsi,
                            style = PoppinsRegular14,
                            color = Color.Black,
                            textAlign = TextAlign.Justify,
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // button video
                        VideoButton(onClick = {
                            // Tambahkan aksi navigasi atau tindakan lainnya di sini
                        })

                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }

            // Floating Comment Button
            FloatingCommentButton(
                commentCount = 60,
                onClick = {
                    navController.navigate("komentar_artikel")
                },
            )
        }
    }
}

@Composable
fun VideoButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenPrimary
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .height(48.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Video",
                style = PoppinsMedium14,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun FloatingCommentButton(
    commentCount: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenLight,
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .height(48.dp)
                .shadow(4.dp, RoundedCornerShape(50)),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "Comment Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = commentCount.toString(),
                    style = PoppinsRegular14,
                    color = Color.Black
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewDetailArticle() {
    val navControllerPreview = rememberNavController()
    Agrofy_AppTheme {
        DetailArtikelScreen(
            article = Articles(
                id = 2,
                judul = "Jerami Sebagai Sumber Bahan Organik Untuk Kesuburan Tanah",
                konten = "Penurunan produktivitas sawah di Indonesia banyak disebabkan oleh degradasi kesuburan tanah...",
                deskripsi = """
                Penurunan produktivitas sawah di Indonesia banyak disebabkan oleh degradasi kesuburan tanah, terutama karena rendahnya kandungan bahan organik. Saat ini, lebih dari 65% lahan sawah irigasi di Indonesia memiliki kandungan bahan organik kurang dari 2%, padahal standar kesuburan minimal adalah 3%. Salah satu penyebab utamanya adalah penggunaan pupuk kimia secara terus-menerus tanpa disertai pemupukan organik yang cukup. Kebiasaan membakar jerami setelah panen juga berkontribusi terhadap penurunan kesuburan tanah, karena menyebabkan hilangnya nutrisi penting seperti nitrogen, fosfor, dan kalium.

                Pembakaran jerami tidak hanya mengurangi kesuburan tanah, tetapi juga berdampak negatif pada kualitas udara dan kesehatan lingkungan. Selain itu, hilangnya bahan organik tanah berakibat pada berkurangnya mikroba yang penting bagi keseimbangan ekosistem lahan sawah. Oleh karena itu, pemanfaatan jerami sebagai pupuk organik sangat penting. Penggunaan jerami yang dikembalikan ke lahan sawah bisa membantu menjaga ketersediaan nutrisi, meningkatkan populasi mikroba tanah, dan memperbaiki struktur tanah.

                Pengolahan jerami menjadi kompos merupakan solusi ramah lingkungan yang efektif. Kompos jerami dapat menjadi alternatif yang lebih murah daripada pupuk anorganik dan membantu meningkatkan hasil panen. Kombinasi penggunaan pupuk organik dan anorganik memberikan hasil optimal, baik dalam hal produktivitas maupun kesuburan tanah jangka panjang. Langkah ini juga mendukung pertanian yang lebih berkelanjutan dan mengurangi ketergantungan pada pupuk kimia sintetis.
                
                 Anda bisa lihat disini cara membuat pupuk bokashi (Bahan Organik Kaya Sumber Hayati) dari jerami
                """.trimIndent(),
                tanggal = 20241025,
                kategori = "Jagung",
                photo = R.drawable.video_thumb,
                author = "Willy Candra"
            ),
            navController = navControllerPreview
        )
    }
}