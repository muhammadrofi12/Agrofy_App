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
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Videos
import com.example.agrofy_app.ui.components.VideoPlayer
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsRegular20
import com.example.agrofy_app.ui.theme.PoppinsSemiBold20
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DetailVideoScreen(
    video: Videos,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top Bar
        TopBar(navController)

        Spacer(modifier = Modifier.height(24.dp))

        // Video Player
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            VideoPlayer(videoUrl = video.file_video)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Video Details
        VideoDetails(video)
    }
}

@Composable
private fun TopBar(
    navController: NavController
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
                onClick = { navController.navigate("video") },
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

@Composable
fun VideoDetails(
    video: Videos
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        // Judul
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${video.judul}",
                style = PoppinsSemiBold20,
                color = Color.Black
            )
        }

        // Kalender dan Author
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Kalender
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Kalender",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = formatDate(video.tanggal),
                    style = PoppinsRegular14,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentDescription = "Circle",
                    modifier = Modifier
                        .size(6.dp)
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
                    text = video.author,
                    style = PoppinsRegular14,
                    color = Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = video.deskripsi,
            style = PoppinsRegular14,
            color = Color.Gray
        )
    }
}

fun formatDate(date: Int): String {
    val dateStr = date.toString()
    val parser = SimpleDateFormat("yyyyMMdd", Locale("id"))
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id"))

    return try {
        val parsedDate = parser.parse(dateStr)
        formatter.format(parsedDate)
    } catch (e: Exception) {
        dateStr
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailVideo() {
    val NavControllerPreview = rememberNavController()
    Agrofy_AppTheme {
        DetailVideoScreen(
            video = Videos(
                id = 1,
                judul = "Pembuatan Briket Dari Bongol Jagung",
                sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
                deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
                tanggal = 20241025,
                kategori = "Jagung",
                duration = "03:35",
                photo = R.drawable.video_thumb,
                file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
                author = "Willy Candra"
            ),
            navController = NavControllerPreview
        )
    }
}
