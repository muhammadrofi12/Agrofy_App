package com.example.agrofy_app.ui.screen.profil

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.agrofy_app.R
import com.example.agrofy_app.models.user.ProfileRequest
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.viewmodels.user.ProfileViewModel

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
                // Foto Profil
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = photoUri ?: profile?.foto?.let {
                                "https://example.com/profile/$it"
                            } ?: R.drawable.default_profile
                        ),
                        contentDescription = "Foto Profil",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Button(
                    onClick = {
                        if (photoUri != null) {
                            isUploadingPhoto = true
                            profileViewModel.uploadPhoto(photoUri!!) { success, message ->
                                isUploadingPhoto = false
                                Toast.makeText(
                                    context,
                                    if (success) "Foto berhasil diperbarui" else "Gagal: $message",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    },
                    enabled = photoUri != null && !isUploadingPhoto,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isUploadingPhoto) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    } else {
                        Text("Simpan Foto")
                    }
                }

                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Pilih Foto")
                }

                // Input Data Profil
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
                                namaLengkap = if (namaLengkap.isNotBlank()) namaLengkap else profile?.namaLengkap ?: "",
                                email = if (email.isNotBlank()) email else profile?.email ?: ""
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isUpdatingProfile) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    } else {
                        Text("Simpan Profil")
                    }
                }
            }
        }
    }
}

