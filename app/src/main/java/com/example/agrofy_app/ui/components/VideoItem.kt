package com.example.agrofy_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.models.pemberdayaan.VideoResponse
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold16
import com.example.agrofy_app.ui.theme.PoppinsRegular12

@Composable
fun VideoItem(
    video: VideoResponse,
    navController: NavController,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 4.dp)
            .clickable {
                navController.navigate("video_detail/${video.id}")
            },
        shape = RoundedCornerShape(12.dp),
    ) {
        // Thumbnail img
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            // Gambar utama menggunakan AsyncImage untuk load dari URL
            AsyncImage(
                model = "https://73zqc05b-3000.asse.devtunnels.ms/thumb/${video.thumbnail}",
                contentDescription = video.judulVideo,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_image)
            )

            // Warna Gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                GreenPrimary
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            // Text Duration
//            Box(
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .padding(8.dp)
//                    .background(Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(12.dp))
//                    .padding(horizontal = 8.dp, vertical = 4.dp)
//            ) {
//                Text(
//                    text = video.,
//                    style = PoppinsMedium8,
//                    color = Color.White
//                )
//            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, end = 12.dp, bottom = 10.dp)
            ) {
                // Video judul
                Text(
                    text = video.judulVideo,
                    style = PoppinsBold16,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Video description
                Text(
                    text = video.deskripsi.replace("<[^>]*>".toRegex(), ""), // mengambil data tag HTML
                    style = PoppinsRegular12,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewVideoItem() {
//    Agrofy_AppTheme { VideoItem(
//        video = Videos(
//            1,
//            "Pembuatan Briket Dari Bongol Jagung",
//            sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
//            deskripsi = """
//                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
//                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
//                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
//                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus.
//                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
//                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
//                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
//
//                Keunggulan 🌟
//                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
//                2. Ekonomis: Lebih murah dan mudah didapat.
//                3. Stabil: Panas yang konsisten dan emisi rendah.
//
//                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
//                """.trimIndent(),
//            25-10-2024,
//             "Jagung",
//            "03:35",
//            R.drawable.video_thumb,
//            "https://youtu.be/mMCNwUJMKNI?si=cmwTjQgzm4falwCm",
//            "Willy Candra",
//        )
//    ) }
//}

