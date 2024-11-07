package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Videos
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.components.VideoPlayer
import com.example.agrofy_app.ui.theme.Agrofy_AppTheme
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold20
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DetailVideoScreen(
    video: Videos,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Video Pembelajaran"
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {

            // Video Player
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                VideoPlayer(videoUrl = video.file_video)
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    // Video Details
                    VideoDetails(video)

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
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
                text = video.judul,
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
        formatter.format(parsedDate as Date)
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
