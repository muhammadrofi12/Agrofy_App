@file:Suppress("DEPRECATION")

package com.example.agrofy_app.ui.screen.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agrofy_app.R
import com.example.agrofy_app.ui.components.TopAppBar
import com.example.agrofy_app.ui.theme.GreenPrimary
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.IntOffset


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddForumScreen(navController: NavController) {
    var postText by remember { mutableStateOf("") }

    val keyboardState by keyboardAsState()

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
                onClick = { /* Action menambah diskusi */ },
                containerColor = GreenPrimary,
                contentColor = Color.White,
                modifier = Modifier
                    .height(126.dp)
                    .width(60.dp)
                    .padding(bottom = 62.dp)
                    .offset {
                        IntOffset(0, -keyboardState.height)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Kirim Postingan",
                    modifier = Modifier.size(38.dp)
                )
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
                    Image(
                        painter = painterResource(R.drawable.profil),
                        contentDescription = "Profile picture",
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
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text("Apa yang sedang terjadi?") },
                        textStyle = TextStyle(color = Color.Black)
                    )
                }
            }

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
                    // add image
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(GreenPrimary, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { /* Handle image attachment */ }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_image),
                                contentDescription = "Attach image",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Add Location
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
    val height: Int
)


@Preview(showBackground = true)
@Composable
fun AddForumScreenPreview() {
    val dummyNavController = rememberNavController()
    AddForumScreen(navController = dummyNavController)
}
