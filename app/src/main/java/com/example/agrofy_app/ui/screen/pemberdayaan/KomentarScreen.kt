package com.example.agrofy_app.ui.screen.pemberdayaan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agrofy_app.R
import com.example.agrofy_app.data.DummyData
import com.example.agrofy_app.models.Comments
import kotlinx.coroutines.launch
import coil.compose.AsyncImage

@Composable
fun KomentarScreen() {
    val (newCommentText, setNewCommentText) = remember { mutableStateOf("") }
    val comments = remember { mutableStateListOf(*DummyData.sampleComments.toTypedArray()) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // List Komentar
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = listState
        ) {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }

        // Input Komentar
        CommentInput(
            text = newCommentText,
            onTextChange = setNewCommentText,
            onSubmit = {
                if (newCommentText.isNotBlank()) {
                    comments.add(
                        Comments(
                            authorName = "Oka Wiyanan",
                            authorImage = R.drawable.profile_oka,
                            content = newCommentText,
                            time = "Baru saja"
                        )
                    )
                    setNewCommentText("")

                    // Launch coroutine to scroll to the bottom of the list
                    coroutineScope.launch {
                        listState.animateScrollToItem(comments.size - 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}




@Composable
fun CommentItem(
    comment: Comments,
    modifier: Modifier = Modifier
) {
    var isLiked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = comment.authorImage,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = comment.authorName,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = comment.content,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = comment.time,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Balas",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }
        }

        IconButton(
            onClick = {
                isLiked = !isLiked
            }
        ) {
            Icon(
                painter = painterResource(
                    id = if (isLiked) R.drawable.ic_love_active else R.drawable.ic_love
                ),
                contentDescription = "Like",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}



@Composable
fun CommentInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_oka),
            contentDescription = "Your Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            placeholder = { Text("Tambahkan Komentar...") },
            maxLines = 1,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onSubmit) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Submit"
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KomentarScreenPreview() {
    KomentarScreen()
}