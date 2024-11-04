package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Videos
import com.example.agrofy_app.ui.components.VideoPlayer
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsRegular16
import com.example.agrofy_app.ui.theme.PoppinsRegular20
import com.example.agrofy_app.ui.theme.PoppinsSemiBold20

@Composable
fun DetailVideoScreen(
    video: Videos,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top Bar
        TopBar(navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Video Player
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            VideoPlayer(videoUrl = video.file_video)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Video Details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = video.judul,
                style = PoppinsSemiBold20,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "By ${video.author}",
                style = PoppinsRegular16,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = video.deskripsi,
                style = PoppinsRegular14,
                color = Color.Gray
            )
        }
    }
}

private @Composable
fun TopBar(
    navController: NavController?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(4.dp, RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController?.navigateUp() },
                modifier = Modifier.size(48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_circle),
                    contentDescription = "Back",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Video Pembelajaran",
                style = PoppinsRegular20,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailVideo() {
    Agrofy_AppTheme {
        DetailVideoScreen(
            video = Videos(
                id = 1,
                judul = "Pembuatan Briket Dari Bongol Jagung",
                deskripsi = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering.",
                tanggal = 25-2024,
                kategori = "Jagung",
                duration = "03:35",
                photo = R.drawable.video_thumb,
                file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
                author = "Willy Candra"
            ),

        )
    }
}
