package com.example.agrofy_app.ui.screen.forum

import android.graphics.Rect
import android.net.Uri
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.viewmodels.forum.AddForumViewModel
import com.example.agrofy_app.viewmodels.user.ProfileViewModel
import java.io.File


@Composable
fun AddForumScreen(navController: NavController) {
    val viewModel: AddForumViewModel = viewModel()
    var postText by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val keyboardState by keyboardAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val postAdded by viewModel.postAdded.collectAsState()

    val profileViewModel: ProfileViewModel = viewModel()
    val profile by profileViewModel.profile.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile()
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Buat Diskusi",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.navigate("forum") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val imageFile = selectedImageUri?.let { uri ->
                        context.contentResolver.openInputStream(uri)?.use { input ->
                            val tempFile = File(context.cacheDir, "forum_image.jpg")
                            tempFile.outputStream().use { output ->
                                input.copyTo(output)
                            }
                            tempFile
                        }
                    }

                    viewModel.addForum(postText, imageFile)
                },
                containerColor = GreenPrimary,
                contentColor = Color.White,
                modifier = Modifier
                    .height(126.dp)
                    .width(60.dp)
                    .padding(bottom = 62.dp)
                    .offset {
                        IntOffset(0, -keyboardState.height)
                    }
                    .alpha(if (postText.isNotBlank() && !isLoading) 1f else 0.5f),
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "Kirim Postingan",
                        modifier = Modifier.size(38.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Profile and input field
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = profile?.foto?.let {
                            "https://73zqc05b-3000.asse.devtunnels.ms/profile/${profile?.foto}"
                        } ?: R.drawable.default_profile,
                        contentDescription = "Profile: ${profile?.namaLengkap ?: "Pengguna"}",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Text input field
                    TextField(
                        value = postText,
                        onValueChange = { postText = it },
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        placeholder = { Text("Apa yang sedang terjadi?") },
                        textStyle = TextStyle(color = Color.Black)
                    )

                }

                // Attached Image Preview
                selectedImageUri?.let { uri ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = uri),
                            contentDescription = "Attached Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        // Remove image button
                        IconButton(
                            onClick = { selectedImageUri = null },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remove Image",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // Bottom Actions
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset {
                        IntOffset(0, -keyboardState.height)
                    }
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Add image
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(GreenPrimary, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                imagePickerLauncher.launch("image/*")
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_image),
                                contentDescription = "Attach image",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Add Location (Placeholder)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(GreenPrimary, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /* Handle location */ }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_location),
                                contentDescription = "Add location",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

    // Handle success and error cases
    LaunchedEffect(postAdded, error) {
        if (postAdded) {
            navController.navigate("forum") {
                popUpTo("forum") { inclusive = true }
            }
        }

        error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun keyboardAsState(): State<KeyboardState> {
    val keyboardState = remember { mutableStateOf(KeyboardState(0)) }
    val view = LocalView.current

    DisposableEffect(view) {
        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            // Ketinggian keyboard
            val adjustedHeight = if (keypadHeight > 0) keypadHeight + (-140) else 0
            keyboardState.value = KeyboardState(adjustedHeight)
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
        }
    }

    return keyboardState
}

data class KeyboardState(
    val height: Int,
)


@Preview(showBackground = true)
@Composable
fun AddForumScreenPreview() {
    val dummyNavController = rememberNavController()
    AddForumScreen(navController = dummyNavController)
}
