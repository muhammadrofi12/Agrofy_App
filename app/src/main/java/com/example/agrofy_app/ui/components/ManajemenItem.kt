@file:Suppress("DEPRECATION")

package com.example.agrofy_app.ui.components

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.models.HasilOlah
import com.example.agrofy_app.models.Limbah
import com.example.agrofy_app.ui.theme.BrownActive
import com.example.agrofy_app.ui.theme.Error
import com.example.agrofy_app.ui.theme.GreenActive
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold12
import com.example.agrofy_app.ui.theme.PoppinsBold16
import com.example.agrofy_app.ui.theme.PoppinsBold20
import com.example.agrofy_app.ui.theme.PoppinsRegular10
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.ui.theme.PoppinsRegular16
import java.io.FileNotFoundException


// List Limbah
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LimbahItem(limbah: Limbah, navController: NavController) {
    var showBottomLimbah by remember { mutableStateOf(false) }
    var showOlahLimbahModal by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
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
//                    Text(
//                        text = "${limbah.berat} kg",
//                        style = PoppinsRegular12,
//                        color = Color.Black
//                    )

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
                                text = "Masuk",
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
                                text = formatDate(limbah.tgglMasuk),
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
                                .clickable { showOlahLimbahModal = true },
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
                IconButton(onClick = { showBottomLimbah = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "Opsi Lanjut",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
    // Modal Olah Limbah
    OlahLimbahModal(
        limbah = limbah,
        showDialog = showOlahLimbahModal,
        onDismiss = { showOlahLimbahModal = false },
        onSave = { jenisOlahan, tanggalMasuk, deskripsi ->
            // Handle saving the processed waste data
        },
        navController = navController
    )

    // Modal Option
    if (showBottomLimbah) {
        ModalBottomSheet(
            onDismissRequest = { showBottomLimbah = false },
            containerColor = Color.White,
            tonalElevation = 0.dp,
            sheetState = rememberModalBottomSheetState(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                // Title
                Text(
                    text = limbah.nama,
                    style = PoppinsBold20,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Edit
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            showBottomLimbah = false
                            // Action Edit
                        }
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "Edit",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Edit",
                        style = PoppinsRegular16,
                        color = Color.Black
                    )
                }

                // Hapus
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            showBottomLimbah = false
                            // Action Hapus
                        }
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Hapus",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Hapus",
                        style = PoppinsRegular16,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}


// List Progress
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressItem(limbah: Limbah, navController: NavController) {
    var showBottomProgress by remember { mutableStateOf(false) }
    var showProgressDialog by remember { mutableStateOf(false) }

    ProgressDialog(
        showDialog = showProgressDialog,
        onDismiss = { showProgressDialog = false },
        onConfirm = { showProgressDialog = false },
        navController = navController
    )

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
//                    Text(
//                        text = "${limbah.berat} kg",
//                        style = PoppinsRegular12,
//                        color = Color.Black
//                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Status & tanggal
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        // Masuk
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                                color = GreenActive
                            ) {
                                Text(
                                    text = "Masuk",
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
                                    text = formatDate(limbah.tgglMasuk),
                                    style = PoppinsRegular10,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        // Tengat / Keluar
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                                color = Error
                            ) {
                                Text(
                                    text = "Tenggat",
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
                                    text = formatDate(limbah.tgglKeluar),
                                    style = PoppinsRegular10,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
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
                                .clickable { showProgressDialog = true }
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
                IconButton(onClick = { showBottomProgress = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = "opsi Lanjut",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
    // Modal Option
    if (showBottomProgress) {
        ModalBottomSheet(
            onDismissRequest = { showBottomProgress = false },
            containerColor = Color.White,
            tonalElevation = 0.dp,
            sheetState = rememberModalBottomSheetState(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                // Title
                Text(
                    text = limbah.nama,
                    style = PoppinsBold20,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Edit
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            showBottomProgress = false
                            // Action Edit
                        }
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "Edit",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Edit",
                        style = PoppinsRegular16,
                        color = Color.Black
                    )
                }

                // Hapus
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            showBottomProgress = false
                            // Action Hapus
                        }
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Hapus",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Hapus",
                        style = PoppinsRegular16,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}


@Composable
fun RiwayatItem(limbah: Limbah) {
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
//                    Text(
//                        text = "${limbah.berat} kg",
//                        style = PoppinsRegular12,
//                        color = Color.Black
//                    )

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
                                text = "Sukses",
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
                                text = formatDate(limbah.tgglMasuk),
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

                    Spacer(modifier = Modifier.height(16.dp))

                    // Button Olah Limbah
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Surface(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { /* action olah limbah */ },
                            color = GreenPrimary,
                            shadowElevation = 4.dp
                        ) {
                            Text(
                                text = "Hapus",
                                style = PoppinsRegular12,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// List Hasil Olah
@Composable
fun HasilOlahCard(
    hasilOlah: HasilOlah,
    onLihatClick: () -> Unit = {},
    onHapusClick: () -> Unit = {},
) {
    var showModal by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var title by remember { mutableStateOf(TextFieldValue(hasilOlah.title)) }
    var jumlah by remember { mutableStateOf(TextFieldValue(hasilOlah.quantity)) }
    var deskripsi by remember { mutableStateOf(TextFieldValue(hasilOlah.deskripsi)) }
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column {
            // Image
            Image(
//                painter = painterResource(id = R.drawable.atap_jerami),
                painter = imageUri?.let { uri ->
                    remember(uri) {
                        val bitmap = try {
                            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                        } catch (e: FileNotFoundException) {
                            null
                        }
                        bitmap?.asImageBitmap()
                    }?.let { bitmap -> androidx.compose.ui.graphics.painter.BitmapPainter(bitmap) }
                        ?: painterResource(id = R.drawable.atap_jerami)
                } ?: painterResource(id = R.drawable.atap_jerami),
                contentDescription = title.text,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

            // Title
            Text(
                text = title.text,
                style = PoppinsBold16,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )

            // Status and Quantity
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(GreenPrimary, shape = RoundedCornerShape(12.dp))
                        .padding(4.dp)
                        .zIndex(2f)
                ) {
                    Text(
                        text = "Ready",
                        color = Color.White,
                        style = PoppinsBold12,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .offset(x = (-20).dp)
                        .zIndex(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .background(GreenLight, shape = RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = GreenPrimary,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(start = 28.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)

                    ) {
                        Text(
                            text = jumlah.text,
                            style = PoppinsBold12
                        )
                    }
                }

            }

            // Lihat Button
            Button(
                onClick = {
                    showModal = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenPrimary
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Lihat",
                    color = Color.White,
                    style = PoppinsRegular12
                )
            }

            // Hapus Button
            Button(
                onClick = onHapusClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .padding(start = 12.dp, end = 12.dp, bottom = 6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.8f)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Hapus",
                    color = Color.White,
                    style = PoppinsRegular12
                )
            }

            // Add some padding at the bottom
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Dialog sebagai modal popup
        if (showModal) {
            Dialog(onDismissRequest = { showModal = false }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Klik untuk mengubah Image (tempatkan logika pemilihan image Anda di sini)
                        Image(
                            painter = imageUri?.let { uri ->
                                remember(uri) {
                                    val bitmap = try {
                                        MediaStore.Images.Media.getBitmap(
                                            context.contentResolver,
                                            uri
                                        )
                                    } catch (e: FileNotFoundException) {
                                        null
                                    }
                                    bitmap?.asImageBitmap()
                                }?.let { bitmap ->
                                    androidx.compose.ui.graphics.painter.BitmapPainter(
                                        bitmap
                                    )
                                }
                                    ?: painterResource(id = R.drawable.atap_jerami)
                            } ?: painterResource(id = R.drawable.atap_jerami),
                            contentDescription = title.text,
                            modifier = Modifier
                                .size(120.dp)
                                .padding(bottom = 16.dp)
                                .clickable { imagePickerLauncher.launch("image/*") },
                            contentScale = ContentScale.Crop
                        )

                        // Input Field untuk Title
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Judul") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )

                        // Input Field untuk Jumlah
                        TextField(
                            value = jumlah,
                            onValueChange = { jumlah = it },
                            label = { Text("Jumlah (Kg)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )

                        // Input Field untuk Deskripsi
                        TextField(
                            value = deskripsi,
                            onValueChange = { deskripsi = it },
                            label = { Text("Deskripsi") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )

                        // Tombol Edit
                        Button(
                            onClick = {
                                // Handle simpan atau edit action di sini
                                showModal = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Text(text = "Edit", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
