package com.example.agrofy_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.agrofy_app.R
import com.example.agrofy_app.models.forum.Comment
import com.example.agrofy_app.models.forum.ForumPost
import com.example.agrofy_app.ui.theme.PoppinsMedium14
import com.example.agrofy_app.ui.theme.PoppinsMedium16
import com.example.agrofy_app.ui.theme.PoppinsRegular14

@Composable
fun ForumDetailHeader(
    post: ForumPost,
) {
    var isLiked by remember { mutableStateOf(false) }
    var currentLikesCount by remember { mutableIntStateOf(post.likesCount) }
    var imageLoadError by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            // User
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                AsyncImage(
//                    model = post.authorProfileImage?.let {
//                        "https://73zqc05b-3000.asse.devtunnels.ms/profile/${post.authorProfileImage}"
//                    } ?: R.drawable.default_profile,
//                    contentDescription = "Profile: ${post.authorName}",
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(CircleShape)
//                )
//
//                Spacer(modifier = Modifier.width(10.dp))
//
//                Text(
//                    text = post.authorName,
//                    style = PoppinsBold16,
//                )
//            }

            // Image
            Spacer(modifier = Modifier.height(8.dp))
            if (!post.imageResource.isNullOrEmpty() && !imageLoadError) {
                AsyncImage(
                    model = "https://73zqc05b-3000.asse.devtunnels.ms/komunitas/${post.imageResource}",
                    contentDescription = "Image: ${post.id}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_image),
                    onError = { imageLoadError = true }
                )
            } else if (imageLoadError) {
                Text(
                    text = "Gagal memuat gambar",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            // Question Text
            Text(
                text = post.question,
                style = PoppinsMedium14,
                textAlign = TextAlign.Justify,
            )

            Spacer(modifier = Modifier.height(10.dp))
            // Likes and Comments
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Likes
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isLiked = !isLiked
                        currentLikesCount += if (isLiked) 1 else -1
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_love_active),
                        contentDescription = "Love icon",
                        modifier = Modifier.size(24.dp),
                        tint = if (isLiked) Color.Red else Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$currentLikesCount",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Comments
                Row(
                    verticalAlignment = Alignment.CenterVertically,
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


@Composable
fun CommentItem(comment: Comment) {
//    var isLiked by remember { mutableStateOf(false) }
//    var currentLikes by remember { mutableIntStateOf(comment.likes) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = comment.userProfileImage?.let {
                    "https://73zqc05b-3000.asse.devtunnels.ms/profile/${comment.userProfileImage}"
                } ?: R.drawable.default_profile,
                contentDescription = "Profile: ${comment.userName}",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = comment.userName,
                style = PoppinsMedium16,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = comment.message,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp),
            textAlign = TextAlign.Justify,
            style = PoppinsRegular14
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Row untuk likes dan reply sebagai teks
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Menampilkan jumlah like
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "$currentLikes suka", fontSize = 12.sp, color = Color.Gray)
//
//                Spacer(modifier = Modifier.width(8.dp))
//
//                Text(
//                    text = "Balas",
//                    fontSize = 12.sp,
//                    color = Color.Blue
//                )
//            }
//
//            // Menambahkan ikon love di sudut kanan
//            Icon(
//                painter = painterResource(R.drawable.ic_love_active),
//                contentDescription = "Love Icon",
//                modifier = Modifier
//                    .size(20.dp)
//                    .clickable {
//                        isLiked = !isLiked
//                        currentLikes += if (isLiked) 1 else -1
//                    },
//                tint = if (isLiked) Color.Red else Color.Gray
//            )
//        }

        // Menampilkan sub-komentar jika ada
//            if (comment.replies.isNotEmpty()) {
//                Spacer(modifier = Modifier.height(8.dp))
//                SubCommentList(subComments = comment.replies)
//            }

    }
}