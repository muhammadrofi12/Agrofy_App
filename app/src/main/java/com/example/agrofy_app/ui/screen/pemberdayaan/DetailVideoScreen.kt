package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.components.VideoPlayer
import com.example.agrofy_app.ui.components.formatISOToDate
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold20
import com.example.agrofy_app.viewmodels.pemberdayaan.DetailVideoViewModel

@Composable
fun DetailVideoScreen(
    videoId: Int,
    navController: NavController,
    viewModel: DetailVideoViewModel = viewModel()
) {
    val video by viewModel.video.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(videoId) {
        viewModel.fetchVideoById(videoId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Detail Video",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        when {
            isLoading -> Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = GreenActive
                )
            }
            error != null -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Error: $error",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    style = PoppinsRegular14
                )
            }
            video != null -> {
                val currentVideo = video!!
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(top = 8.dp)
                ) {
                    // Video Player
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            VideoPlayer(videoUrl = "https://73zqc05b-3000.asse.devtunnels.ms/video/${currentVideo.video}")
                        }
                    }

                    // Video Details
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))

                            // Title
                            Text(
                                text = currentVideo.judulVideo,
                                style = PoppinsSemiBold20,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Date and Author
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "Calendar",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formatISOToDate(currentVideo.createdAt),
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

                                Image(
                                    painter = painterResource(id = R.drawable.ic_author),
                                    contentDescription = "Author",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = currentVideo.namaLengkap,
                                    style = PoppinsRegular14,
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Description
                            Text(
                                text = currentVideo.deskripsi.replace("<[^>]*>".toRegex(), ""),
                                style = PoppinsRegular14,
                                color = Color.Black,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailVideo() {
//    val NavControllerPreview = rememberNavController()
//    Agrofy_AppTheme {
//        DetailVideoScreen(
//            video = Videos(
//                id = 1,
//                judul = "Pembuatan Briket Dari Bongol Jagung",
//                sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
//                deskripsi = """
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
//                tanggal = 20241025,
//                kategori = "Jagung",
//                duration = "03:35",
//                photo = R.drawable.video_thumb,
//                file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
//                author = "Willy Candra"
//            ),
//            navController = NavControllerPreview
//        )
//    }
//}
