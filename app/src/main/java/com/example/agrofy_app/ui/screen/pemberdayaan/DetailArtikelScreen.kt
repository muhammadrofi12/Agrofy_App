package com.example.agrofy_app.ui.screen.pemberdayaan

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.Layout
import android.view.Gravity
import android.widget.TextView
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.components.formatISOToDate
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.PoppinsRegular14
import com.example.agrofy_app.ui.theme.PoppinsSemiBold20
import com.example.agrofy_app.viewmodels.pemberdayaan.DetailArtikelViewModel

@Composable
fun DetailArtikelScreen(
    artikelId: Int,
    navController: NavController,
    viewModel: DetailArtikelViewModel = viewModel(),
) {
    val artikel by viewModel.artikel.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Fetch data on screen launch
    LaunchedEffect(artikelId) {
        viewModel.fetchArtikelById(artikelId)
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
                                Text(
                                    text = article.namaLengkap,
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

                            // Mengambil fungsi HTML di Android
                            AndroidView(
                                factory = { context ->
                                    TextView(context).apply {
                                        textSize = 14f
                                        setTextColor(android.graphics.Color.BLACK)
                                        textAlignment = TextView.TEXT_ALIGNMENT_TEXT_START
                                        gravity = Gravity.START
                                        justify()
                                    }
                                },
                                update = { textView ->
                                    textView.text = Html.fromHtml(article.deskripsi, Html.FROM_HTML_MODE_COMPACT)
                                },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("WrongConstant")
fun TextView.justify() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
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