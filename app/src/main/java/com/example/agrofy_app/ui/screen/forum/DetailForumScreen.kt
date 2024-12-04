@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.agrofy_app.ui.screen.forum

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.CommentItem
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.PoppinsBold18
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.viewmodels.forum.ForumDetailViewModel
import com.example.agrofy_app.viewmodels.user.ProfileViewModel

@Composable
fun DetailForumScreen(
    navController: NavController,
    forumId: Int,
    viewModel: ForumDetailViewModel = viewModel(),
) {
//    val forumPost by viewModel.forumPost.collectAsState()
    val comments by viewModel.comments.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val isPostNotFound by viewModel.isPostNotFound.collectAsState()

    var newCommentText by remember { mutableStateOf("") }

    val profileViewModel: ProfileViewModel = viewModel()
    val profile by profileViewModel.profile.collectAsState()

    LaunchedEffect(forumId) {
        viewModel.loadForumDetails(forumId)
    }

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile()
    }

    // Cek Eror
    error?.let { errorMessage ->
        LaunchedEffect(errorMessage) {
            viewModel.setError("")
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (isPostNotFound) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Forum post not found")
        }
    } else if (error != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Error: $error")
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    navController = navController,
                    title = "Diskusi",
                    img = R.drawable.ic_back_circle,
                    onIconButtonClick = { navController.popBackStack() }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                ) {
//                    forumPost?.let { post ->
//                        ForumDetailHeader(
//                            post
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                    }

                    Text(
                        text = "Comments",
                        style = PoppinsBold18,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                    )

                    LazyColumn {
                        if (comments.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Belum ada komentar. Jadilah yang pertama!",
                                        style = PoppinsMedium14,
                                        color = Color.Gray
                                    )
                                }
                            }
                        } else {
                            items(comments) { comment ->
                                CommentItem(comment)
                            }
                        }
                    }
                }
            }

            // Floating comment input section
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter // Tetapkan alignment pada Box
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(
                            BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
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

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value = newCommentText,
                            onValueChange = { newCommentText = it },
                            modifier = Modifier
                                .weight(1f),
                            placeholder = { Text("Tulis komentar...") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                if (newCommentText.isNotEmpty()) {
                                    viewModel.addComment(forumId, newCommentText)
                                    newCommentText = ""
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_send),
                                contentDescription = "Send",
                                tint = Color(0xFF0381FE),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }

            }
        }
    }
}

