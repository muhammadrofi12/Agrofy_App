package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold20
import com.example.agrofy_app.viewmodels.user.AdminViewModel
import com.example.agrofy_app.viewmodels.pemberdayaan.DetailArtikelViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun DetailArtikelScreen(
    artikelId: Int,
    navController: NavController,
    viewModel: DetailArtikelViewModel = viewModel(),
    adminViewModel: AdminViewModel = viewModel()
) {
    val artikel by viewModel.artikel.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Fetch data on screen launch
    LaunchedEffect(artikelId) {
        viewModel.fetchArtikelById(artikelId)
        adminViewModel.fetchAdmins()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Artikel",
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
            artikel != null -> {
                val article = artikel!!
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    // Artikel Header (Judul, Tanggal, Author)
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = article.judulArtikel,
                                style = PoppinsSemiBold20,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "Calendar",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formatISOToDate(article.createdAt),
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

                                // Mengambil relasi user
                                val adminName = adminViewModel.getAdminNameById(article.userId)
                                Text(
                                    text = adminName ?: "Unknown",
                                    style = PoppinsRegular14,
                                    color = Color.Gray
                                )
                            }
                        }
                    }

                    // Artikel Image
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            AsyncImage(
                                model = "https://73zqc05b-3000.asse.devtunnels.ms/artikel/${article.thumbnail}",
                                contentDescription = article.judulArtikel,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF8B4513),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                placeholder = painterResource(id = R.drawable.ic_image)
                            )
                        }
                    }

                    // Artikel Content
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = article.deskripsi.replace("<[^>]*>".toRegex(), ""),
                                style = PoppinsRegular14,
                                color = Color.Black,
                                textAlign = TextAlign.Justify
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Video Button
                            VideoButton(onClick = {
                                // Tambahkan aksi navigasi atau tindakan lainnya di sini
                            })

                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
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

fun formatISOToDate(isoString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(isoString)
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        outputFormat.format(date!!)
    } catch (e: Exception) {
        "Invalid Date"
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewDetailArticle() {
//    val navControllerPreview = rememberNavController()
//    Agrofy_AppTheme {
//        DetailArtikelScreen(
//            article = Articles(
//                id = 2,
//                judul = "Jerami Sebagai Sumber Bahan Organik Untuk Kesuburan Tanah",
//                deskripsi = """
//                Penurunan produktivitas sawah di Indonesia banyak disebabkan oleh degradasi kesuburan tanah, terutama karena rendahnya kandungan bahan organik. Saat ini, lebih dari 65% lahan sawah irigasi di Indonesia memiliki kandungan bahan organik kurang dari 2%, padahal standar kesuburan minimal adalah 3%. Salah satu penyebab utamanya adalah penggunaan pupuk kimia secara terus-menerus tanpa disertai pemupukan organik yang cukup. Kebiasaan membakar jerami setelah panen juga berkontribusi terhadap penurunan kesuburan tanah, karena menyebabkan hilangnya nutrisi penting seperti nitrogen, fosfor, dan kalium.
//
//                Pembakaran jerami tidak hanya mengurangi kesuburan tanah, tetapi juga berdampak negatif pada kualitas udara dan kesehatan lingkungan. Selain itu, hilangnya bahan organik tanah berakibat pada berkurangnya mikroba yang penting bagi keseimbangan ekosistem lahan sawah. Oleh karena itu, pemanfaatan jerami sebagai pupuk organik sangat penting. Penggunaan jerami yang dikembalikan ke lahan sawah bisa membantu menjaga ketersediaan nutrisi, meningkatkan populasi mikroba tanah, dan memperbaiki struktur tanah.
//
//                Pengolahan jerami menjadi kompos merupakan solusi ramah lingkungan yang efektif. Kompos jerami dapat menjadi alternatif yang lebih murah daripada pupuk anorganik dan membantu meningkatkan hasil panen. Kombinasi penggunaan pupuk organik dan anorganik memberikan hasil optimal, baik dalam hal produktivitas maupun kesuburan tanah jangka panjang. Langkah ini juga mendukung pertanian yang lebih berkelanjutan dan mengurangi ketergantungan pada pupuk kimia sintetis.
//
//                 Anda bisa lihat disini cara membuat pupuk bokashi (Bahan Organik Kaya Sumber Hayati) dari jerami
//                """.trimIndent(),
//                tanggal = 20241025,
//                kategori = "Jagung",
//                photo = R.drawable.video_thumb,
//                author = "Willy Candra"
//            ),
//            navController = navControllerPreview
//        )
//    }
//}