@file:Suppress("DEPRECATION")

package com.example.agrofy_app.ui.components


import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Limbah
import com.example.agrofy_app.ui.theme.BrownActive
import com.example.agrofy_app.ui.theme.BrownPrimary
import com.example.agrofy_app.ui.theme.Error
import com.example.agrofy_app.ui.theme.GreenLight
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold12
import com.example.agrofy_app.ui.theme.PoppinsBold20
import com.example.agrofy_app.ui.theme.PoppinsRegular10
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import java.util.Calendar

@Composable
fun LimbahCardItem(
    title: String,
    expiryDate: String,
    img: Int,
) {
    Card(
        modifier = Modifier
            .width(80.dp)
            .height(140.dp)
            .border(1.dp, BrownPrimary, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = GreenLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = title,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = title,
                style = PoppinsRegular12,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(BrownActive)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = expiryDate,
                    style = PoppinsRegular10,
                    color = Color.White,
                )
            }
        }
    }
}


// Modal Add Limbah
@Composable
fun AddLimbahModal(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (String, String, String, Uri?) -> Unit,
) {
    var namaLimbah by remember { mutableStateOf("") }
    var tanggalMasuk by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Image Picker
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    // Date Picker
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            tanggalMasuk = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Title
                    Text(
                        text = "Tambah Limbah",
                        style = PoppinsBold12,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Nama Limbah
                    TextFieldWithLabel(
                        label = "Nama Limbah",
                        value = namaLimbah,
                        onValueChange = { namaLimbah = it }
                    )

                    // Tanggal Masuk - Menggunakan DatePicker
                    Text(
                        text = "Tanggal Masuk",
                        style = PoppinsRegular12,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    TextField(
                        value = tanggalMasuk,
                        onValueChange = { tanggalMasuk = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { datePickerDialog.show() },
                        enabled = false,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(0xFFF5F5F5),
                            disabledTextColor = Color.Black,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )

                    // Deskripsi
                    TextFieldWithLabel(
                        label = "Deskripsi",
                        value = deskripsi,
                        onValueChange = { deskripsi = it }
                    )

                    // Unggah Gambar
                    Text(
                        text = "Unggah Gambar",
                        style = PoppinsRegular12,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .clickable { imagePickerLauncher.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        if (imageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(model = imageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier.size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Upload Image",
                                modifier = Modifier.size(40.dp),
                                tint = Color.Gray
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Cancel Button
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Batal",
                                style = PoppinsRegular12,
                                color = Color.White
                            )
                        }

                        // Simpan Button
                        val isActive =
                            namaLimbah.isNotBlank()  && tanggalMasuk.isNotBlank()
                        Button(
                            onClick = {
                                if (isActive) {
                                    onSave(namaLimbah, tanggalMasuk, deskripsi, imageUri)
                                    onDismiss()
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isActive) GreenPrimary else Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = isActive
                        ) {
                            Text(
                                text = "Simpan",
                                style = PoppinsRegular12,
                                color = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}

// Modal Add Hasil Olahan Limbah
@Composable
fun AddHasilOlahModal(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (String, String, Uri?) -> Unit,
) {
    var namaOlahan by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher untuk memilih gambar dari galeri
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Title
                    Text(
                        text = "Tambah Hasil Olahan",
                        style = PoppinsBold12,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Nama Limbah
                    TextFieldWithLabel(
                        label = "Nama Olahan",
                        value = namaOlahan,
                        onValueChange = { namaOlahan = it }
                    )

                    // Deskripsi
                    TextFieldWithLabel(
                        label = "Deskripsi",
                        value = deskripsi,
                        onValueChange = { deskripsi = it }
                    )

                    // Unggah Gambar
                    Text(
                        text = "Unggah Gambar",
                        style = PoppinsRegular12,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .clickable { imagePickerLauncher.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(model = selectedImageUri),
                                contentDescription = "Selected Image",
                                modifier = Modifier.size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Upload Image",
                                modifier = Modifier.size(40.dp),
                                tint = Color.Gray
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Cancel Button
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Batal",
                                style = PoppinsRegular12,
                                color = Color.White
                            )
                        }

                        // Simpan Button
                        val isActive =
                            namaOlahan.isNotBlank() && deskripsi.isNotBlank()
                        Button(
                            onClick = {
                                if (isActive) {
                                    onSave(namaOlahan,  deskripsi, selectedImageUri)
                                    onDismiss()
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isActive) GreenPrimary else Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = isActive
                        ) {
                            Text(
                                text = "Simpan",
                                style = PoppinsRegular12,
                                color = Color.White
                            )
                        }
                    }

                }
            }
        }
    }
}

// Modal Olah Limbah
@Composable
fun OlahLimbahModal(
    limbah: Limbah,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (String, String, String) -> Unit,
    navController: NavController,
) {
    var jenisOlahan by remember { mutableStateOf("") }
    var tanggalMasuk by remember { mutableStateOf("") }
    var tanggalSelesai by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }

    // Date Picker for Tanggal Masuk
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialogMasuk = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            tanggalMasuk = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Date Picker for Tanggal Selesai
    val datePickerDialogSelesai = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            tanggalSelesai = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Judul
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = limbah.nama,
                            style = PoppinsBold20,
                            color = Color.Black
                        )
                        AsyncImage(
                            model = limbah.photo,
                            contentDescription = limbah.nama,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Jenis Olahan
                    TextFieldWithLabel(
                        label = "Jenis Olahan",
                        value = jenisOlahan,
                        onValueChange = { jenisOlahan = it }
                    )

                    // Tanggal Section with Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Tanggal Masuk Column
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Tanggal Masuk",
                                style = PoppinsRegular12,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            TextField(
                                value = tanggalMasuk,
                                onValueChange = { tanggalMasuk = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable { datePickerDialogMasuk.show() },
                                enabled = false,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color(0xFFF5F5F5),
                                    disabledTextColor = Color.Black,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                textStyle = PoppinsRegular12
                            )
                        }

                        // Tanggal Selesai Column
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Tanggal Selesai",
                                style = PoppinsRegular12,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            TextField(
                                value = tanggalSelesai,
                                onValueChange = { tanggalSelesai = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable { datePickerDialogSelesai.show() },
                                enabled = false,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color(0xFFF5F5F5),
                                    disabledTextColor = Color.Black,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                textStyle = PoppinsRegular12
                            )
                        }
                    }

                    // Deskripsi
                    TextFieldWithLabel(
                        label = "Deskripsi",
                        value = deskripsi,
                        onValueChange = { deskripsi = it }
                    )

                    // Buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Cancel Button
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Batal",
                                style = PoppinsRegular12,
                                color = Color.White
                            )
                        }

                        // Save Button
                        val isActive = jenisOlahan.isNotBlank() &&
                                tanggalMasuk.isNotBlank() &&
                                tanggalSelesai.isNotBlank()

                        Button(
                            onClick = {
                                if (isActive) {
                                    onSave(jenisOlahan, tanggalMasuk, deskripsi)
                                    onDismiss()
                                }
                                navController.navigate("progress")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isActive) GreenPrimary else Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = isActive
                        ) {
                            Text(
                                text = "Olah Limbah",
                                style = PoppinsRegular12,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    Text(
        text = label,
        style = PoppinsRegular12,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { focusState -> isFocused = focusState.isFocused },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = if (isFocused) Color(0xFFE8F5E9) else Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

// Modal Progress
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    navController: NavController,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Apakah limbah berhasil diolah?",
                        style = PoppinsBold20,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                onDismiss()
                                navController.navigate("riwayat") {
                                    popUpTo(0) { inclusive = true }
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Error,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .weight(2f)
                                .height(34.dp),
                        ) {
                            Text(
                                text = "Gagal",
                                color = Color.White,
                            )
                        }

                        Button(
                            onClick = {
                                onConfirm()
                                navController.navigate("riwayat") {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenPrimary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(2f)
                                .height(34.dp)
                        ) {
                            Text(
                                text = "Sukses",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
