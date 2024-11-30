package com.example.agrofy_app.ui.screen.profil

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.agrofy_app.R
import com.example.agrofy_app.models.user.ProfileRequest
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.viewmodels.user.ProfileViewModel
import java.io.File
import java.io.InputStream

@Composable
fun EditProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
) {
    val profile by profileViewModel.profile.collectAsState()
    val context = LocalContext.current

    var namaLengkap by remember { mutableStateOf(profile?.namaLengkap ?: "") }
    var email by remember { mutableStateOf(profile?.email ?: "") }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var isUploadingPhoto by remember { mutableStateOf(false) }
    var isUpdatingProfile by remember { mutableStateOf(false) }

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isUpdatingPassword by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile()
    }

    LaunchedEffect(profile) {
        namaLengkap = profile?.namaLengkap ?: ""
        email = profile?.email ?: ""
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> photoUri = uri }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Edit Profil",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Section Foto
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Foto Profil
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = profile?.foto?.let {
                                "https://73zqc05b-3000.asse.devtunnels.ms/profile/${profile?.foto}"
                            } ?: R.drawable.default_profile
                        ),
                        contentDescription = "Foto Profil",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                if (photoUri != null) {
                                    isUploadingPhoto = true
                                    val file = getFileFromUri(context, photoUri!!)
                                    if (file != null) {
                                        profileViewModel.uploadPhoto(Uri.fromFile(file)) { success, message ->
                                            isUploadingPhoto = false
                                            Toast.makeText(
                                                context,
                                                if (success) "Foto berhasil diperbarui" else "Gagal: $message",
                                                Toast.LENGTH_SHORT,
                                            ).show()
                                        }
                                    } else {
                                        isUploadingPhoto = false
                                        Toast.makeText(
                                            context,
                                            "File tidak valid atau tidak dapat diakses",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                            enabled = photoUri != null && !isUploadingPhoto,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                            shape = RoundedCornerShape(6.dp),
                        ) {
                            if (isUploadingPhoto) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Text("Simpan Foto")
                            }
                        }

                        Button(
                            onClick = { launcher.launch("image/*") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                            shape = RoundedCornerShape(6.dp),
                        ) {
                            Text("Pilih Foto")
                        }
                    }
                }

                // Section Data Profile
                OutlinedTextField(
                    value = namaLengkap,
                    onValueChange = { namaLengkap = it },
                    label = { Text("Nama Lengkap") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        isUpdatingProfile = true
                        profileViewModel.editProfile(
                            ProfileRequest(
                                namaLengkap = namaLengkap.ifBlank { profile?.namaLengkap ?: "" },
                                email = email.ifBlank { profile?.email ?: "" }
                            ),
                            onSuccess = {
                                isUpdatingProfile = false
                                Toast.makeText(
                                    context,
                                    "Profil berhasil diperbarui",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onError = { errorMessage ->
                                isUpdatingProfile = false
                                Toast.makeText(
                                    context,
                                    "Gagal: $errorMessage",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    },
                    enabled = !isUpdatingProfile,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    if (isUpdatingProfile) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Simpan Profil")
                    }
                }

                // Section Password
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Kata Sandi Baru") },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                painter = painterResource(
                                    if (showPassword) R.drawable.show_eye else R.drawable.ic_eye
                                ),
                                contentDescription = if (showPassword) "Sembunyikan Kata Sandi" else "Lihat Kata Sandi",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Konfirmasi Kata Sandi Baru") },
                    visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                            Icon(
                                painter = painterResource(
                                    if (showConfirmPassword) R.drawable.show_eye else R.drawable.ic_eye
                                ),
                                contentDescription = if (showConfirmPassword) "Sembunyikan Kata Sandi" else "Lihat Kata Sandi",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        Log.d(
                            "EditPassword",
                            "Password: $newPassword, Confirm Password: $confirmPassword"
                        )

                        if (newPassword.isBlank() || confirmPassword.isBlank()) {
                            Toast.makeText(
                                context,
                                "Password tidak boleh kosong",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        if (newPassword != confirmPassword) {
                            Toast.makeText(context, "Kata sandi tidak cocok", Toast.LENGTH_SHORT)
                                .show()
                            return@Button
                        }

                        isUpdatingPassword = true
                        profileViewModel.editPassword(
                            newPassword,
                            confirmPassword,
                            onSuccess = {
                                isUpdatingPassword = false
                                Toast.makeText(
                                    context,
                                    "Kata sandi berhasil diperbarui",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onError = { errorMessage ->
                                isUpdatingPassword = false
                                Log.e("EditPassword", "Error: $errorMessage")
                                Toast.makeText(
                                    context,
                                    "Gagal memperbarui kata sandi: $errorMessage",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    },
                    enabled = !isUpdatingPassword,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    if (isUpdatingPassword) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Simpan Kata Sandi")
                    }
                }

            }
        }
    }
}

fun getFileFromUri(context: Context, uri: Uri): File? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val mimeType = context.contentResolver.getType(uri) ?: "unknown"
        val fileExtension = MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(mimeType)
            ?: uri.toString().substringAfterLast(".", "")

        val validExtensions = listOf("jpg", "jpeg", "png")

        if (fileExtension.lowercase() in validExtensions && mimeType.startsWith("image")) {
            val tempFile = File.createTempFile("upload", ".$fileExtension", context.cacheDir)
            tempFile.outputStream().use { outputStream ->
                inputStream?.copyTo(outputStream)
            }
            Log.d("File Upload", "File info: ${tempFile.name}, mimeType: $mimeType")
            tempFile
        } else {
            Log.e("File Upload", "Invalid file type: $mimeType or extension: $fileExtension")
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("File Upload", "Error converting URI to File: ${e.message}", e)
        null
    }
}
