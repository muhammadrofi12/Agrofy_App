package com.example.agrofy_app.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(videoUrl: String, modifier: Modifier = Modifier) {
    // Create a remember block to maintain the player instance
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    // Prepare the player with the media item
    val mediaItem = MediaItem.fromUri(videoUrl)
    player.setMediaItem(mediaItem)
    player.prepare()
    player.playWhenReady = true

    // Clean up the player when the composable leaves the composition
    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    // Use AndroidView to show the ExoPlayer's PlayerView
    AndroidView(factory = { context ->
        PlayerView(context).apply {
            this.player = player
            useController = true
        }
    }, modifier = modifier.aspectRatio(16 / 9f) // Ganti dengan aspek rasio yang sesuai
    )


}
