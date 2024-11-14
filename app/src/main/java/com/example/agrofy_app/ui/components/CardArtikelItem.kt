package com.example.agrofy_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Articles
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.*

@Composable
fun CardArtikelItem(article: Articles, navController: NavController, modifier: Modifier = Modifier) {
    // State untuk Bookmark
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(120.dp)
            .height(150.dp)
            .clickable {
                println("Artikel diklik: ${article.id}")
                navController.navigate("artikel_detail/${article.id}")
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Gambar utama
            Image(
                painter = painterResource(id = article.photo),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Warna Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, GreenPrimary),
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(4.dp))
                    // Judul
                    Text(
                        text = article.judul,
                        style = PoppinsMedium14.copy(color = Color.White),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                // Deskripsi
                Text(
                    text = article.deskripsi,
                    style = PoppinsRegular10.copy(color = Color.White),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                )
            }

            Icon(
                painter = painterResource(
                    id = if (isBookmarked) R.drawable.bookmark_bold else R.drawable.bookmark,
                ),
                contentDescription = null,
                tint = Warning,
                modifier = Modifier
                    .size(42.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clickable {
                        isBookmarked = !isBookmarked
                    }
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomCard() {
    val NavControllerPreview = rememberNavController()
    Agrofy_AppTheme {
        CardArtikelItem(
            article = Articles(
                1,
                "\uD83C\uDF3DTransformasi Bonggol Jagung Menjadi Briket Arang Berkualitas\uD83C\uDF31",
                deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
                25-10-2024,
                "Jagung",
                R.drawable.video_thumb,
                "Willy Candra",
            ),
            modifier = Modifier.padding(16.dp),
            navController = NavControllerPreview

        )
    }
}
