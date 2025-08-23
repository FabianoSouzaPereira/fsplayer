package com.fabianospdev.fsplayer.features.player.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun PlayerScreen(player: ExoPlayer) {
    val context = LocalContext.current
    var isFullscreen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // ExoPlayer embutido em Compose
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    this.player = player
                    useController = true
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )

        // Botão fullscreen toggle
        IconButton(
            onClick = {
                val activity = (context as? Activity)
                isFullscreen = !isFullscreen
                activity?.requestedOrientation = if (isFullscreen) {
                    ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
        ) {
            Icon(
                imageVector = if (isFullscreen) Icons.Default.Close else Icons.Default.PlayArrow,
                contentDescription = "Fullscreen",
                tint = Color.White
            )
        }
    }
}
