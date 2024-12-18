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
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    val mediaItem = MediaItem.fromUri(videoUrl)
    player.setMediaItem(mediaItem)
    player.prepare()
    player.playWhenReady = true

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    AndroidView(factory = { context ->
        PlayerView(context).apply {
            this.player = player
            useController = true
        }
    }, modifier = modifier.aspectRatio(16 / 9f)
    )


}
