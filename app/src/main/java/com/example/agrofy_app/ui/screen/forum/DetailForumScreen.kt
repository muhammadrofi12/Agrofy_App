package com.example.agrofy_app.ui.screen.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyDataForum
import com.example.agrofy_app.ui.components.DetailForum
import com.example.agrofy_app.ui.components.TopAppBar

@Composable
fun DetailForumScreen(
    navController: NavController,
    postId: String?,
) {
    // mengambil data
    val post = remember(postId) {
        postId?.let { DummyDataForum.getForumPostById(it) }
    }

    var newCommentText by remember { mutableStateOf("") }

    if (post == null) {
        // cek kondisi apakah ada postingan
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Post not found",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Diskusi",
                img = R.drawable.ic_back_circle,
                onIconButtonClick = { navController.navigate("forum") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 20.dp)
        ) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
            ) {
                DetailForum(
                    authorName = post.authorName,
                    question = post.question,
                    likesCount = post.likesCount,
                    commentsCount = post.comments.size,
                    imageResource = post.imageResource,
                    comments = post.comments
                )
            }

            // Floating comment input section
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_author),
                        contentDescription = "Profile Icon",
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
                                // Handle new comment
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

@Preview(showBackground = true)
@Composable
fun PreviewDetailForumScreen() {
    DetailForumScreen(
        navController = rememberNavController(),
        postId = "dummyPostId"
    )
}
