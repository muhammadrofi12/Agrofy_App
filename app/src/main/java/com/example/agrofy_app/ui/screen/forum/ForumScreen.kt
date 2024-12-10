@file:Suppress("DEPRECATION")

package com.example.agrofy_app.ui.screen.forum

import android.text.Html
import android.view.Gravity
import android.widget.TextView
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.models.forum.ForumPost
import com.example.agrofy_app.ui.components.BottomNavigationBar
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.components.formatISOToDate
import com.example.agrofy_app.ui.screen.pemberdayaan.justify
import com.example.agrofy_app.ui.theme.GreenPrimary
import com.example.agrofy_app.ui.theme.PoppinsBold16
import com.example.agrofy_app.ui.theme.PoppinsRegular12
import com.example.agrofy_app.viewmodels.forum.ForumViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ForumScreen(
    navController: NavController,
    viewModel: ForumViewModel = viewModel(),
) {
    val forumPosts by viewModel.forumPosts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Refesh
    val isRefreshing = isLoading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                title = "Forum Diskusi",
                img = R.drawable.ic_forum_diskusi,
                isIconButtonEnabled = false,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_forum") },
                containerColor = GreenPrimary,
                contentColor = Color.White,
                modifier = Modifier
                    .height(120.dp)
                    .width(60.dp)
                    .padding(bottom = 56.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_button),
                    contentDescription = "Add",
                    modifier = Modifier.size(38.dp)
                )
            }
        }
    ) { paddingValues ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.fetchForumPosts() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = GreenPrimary
                    )
                } else if (error != null) {
                    Text(
                        text = "Error: $error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(forumPosts) { post ->
                            ForumPost(
                                navController = navController,
                                post = post
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }

                BottomNavigationBar(
                    navController = navController,
                    onItemSelected = { /* Implementasi aksi */ },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                )
            }
        }

    }
}


@Composable
fun ForumPost(
    navController: NavController,
    post: ForumPost,
) {
//    var isLiked by remember { mutableStateOf(false) }
//    var currentLikesCount by remember { mutableIntStateOf(post.likesCount) }
    var imageLoadError by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp, top = 12.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Author Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = post.authorProfileImage?.let {
                        "https://73zqc05b-3000.asse.devtunnels.ms/profile/$it"
                    } ?: R.drawable.default_profile,
                    contentDescription = "Profile: ${post.authorName}",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = post.authorName,
                        style = PoppinsBold16
                    )

                    // Kapan dibuat
                    Text(
                        text = formatISOToDate(post.created),
                        style = PoppinsRegular12
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            // Post Image
            if (!post.imageResource.isNullOrEmpty() && !imageLoadError) {
                AsyncImage(
                    model = "https://73zqc05b-3000.asse.devtunnels.ms/komunitas/${post.imageResource}",
                    contentDescription = "Image: ${post.id}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_image), // Tambahkan placeholder
                    onError = { imageLoadError = true }
                )
            } else if (imageLoadError) {
                Text(
                    text = "Gagal memuat gambar",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Red
                )
            }

            // Question Text
            Spacer(modifier = Modifier.height(10.dp))
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
                    textView.text = Html.fromHtml(post.question, Html.FROM_HTML_MODE_COMPACT)
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Likes and Comments
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Likes
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.clickable {
//                        isLiked = !isLiked
//                        currentLikesCount += if (isLiked) 1 else -1
//                    }
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_love_active),
//                        contentDescription = "Love icon",
//                        modifier = Modifier.size(24.dp),
//                        tint = if (isLiked) Color.Red else Color.Gray
//                    )
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        text = "$currentLikesCount",
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                }

                Spacer(modifier = Modifier.width(16.dp))

                // Comments
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        navController.navigate("detail_forum/${post.id}")
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_comment),
                        contentDescription = "Comment icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.commentsCount}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForumScreenPreview() {
    val dummyNavController = rememberNavController()
    ForumScreen(navController = dummyNavController)
}
