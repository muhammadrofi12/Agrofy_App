package com.example.agrofy_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Limbah
import com.example.agrofy_app.ui.screen.pemberdayaan.formatDate
import com.example.agrofy_app.ui.theme.BrownActive
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold12
import com.example.agrofy_app.ui.theme.PoppinsBold16
import com.example.agrofy_app.ui.theme.PoppinsRegular10
import com.example.agrofy_app.ui.theme.PoppinsRegular12


// List Limbah
@Composable
fun LimbahItem(limbah: Limbah) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GreenLight),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = limbah.photo,
                    contentDescription = limbah.nama,
                    modifier = Modifier
                        .size(height = 220.dp, width = 150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Name and Weight
                    Text(
                        text = limbah.nama,
                        style = PoppinsBold16
                    )
                    Text(
                        text = "${limbah.berat} kg",
                        style = PoppinsRegular12,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Status & tanggal
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                            color = GreenActive
                        ) {
                            Text(
                                text = "On Progress",
                                style = PoppinsRegular10,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                            color = BrownActive,
                        ) {
                            Text(
                                text = formatDate(limbah.tggl_masuk),
                                style = PoppinsRegular10,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Deskripsi
                    Text(
                        text = "Deskripsi:",
                        style = PoppinsBold12
                    )
                    Text(
                        text = limbah.deskripsi,
                        style = PoppinsRegular12,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Button Olah Limbah
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Siap diolah?",
                            style = PoppinsRegular10,
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { /* action olah limbah */ },
                            color = GreenPrimary,
                            shadowElevation = 4.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Olah Limbah",
                                    style = PoppinsRegular12,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Icon(
                                    painter = painterResource(id = R.drawable.back_vector_right),
                                    contentDescription = "Arrow Right",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            // icon titik tiga
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Action opsi lanjut */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "opsi Lanjut",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


        }
    }
}


// List Progress
@Composable
fun ProgressItem(limbah: Limbah) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GreenLight),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = limbah.photo,
                    contentDescription = limbah.nama,
                    modifier = Modifier
                        .size(height = 220.dp, width = 150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Name and Weight
                    Text(
                        text = limbah.nama,
                        style = PoppinsBold16
                    )
                    Text(
                        text = "${limbah.berat} kg",
                        style = PoppinsRegular12,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Status & tanggal
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                            color = GreenActive
                        ) {
                            Text(
                                text = "On Progress",
                                style = PoppinsRegular10,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                            color = BrownActive,
                        ) {
                            Text(
                                text = formatDate(limbah.tggl_masuk),
                                style = PoppinsRegular10,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Deskripsi
                    Text(
                        text = "Deskripsi:",
                        style = PoppinsBold12
                    )
                    Text(
                        text = limbah.deskripsi,
                        style = PoppinsRegular12,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Button Selesai
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { /* action olah limbah */ }
                                .align(Alignment.CenterEnd),
                            color = GreenPrimary,
                            shadowElevation = 4.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "Selesai",
                                    style = PoppinsRegular12,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Icon(
                                    painter = painterResource(id = R.drawable.back_vector_right),
                                    contentDescription = "Arrow Right",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            // icon titik tiga
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Action opsi lanjut */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "opsi Lanjut",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


        }
    }
}

