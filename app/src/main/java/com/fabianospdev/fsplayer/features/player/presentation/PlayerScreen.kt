// PlayerScreen.kt
package com.fabianospdev.fsplayer.features.player.presentation

import android.app.Activity
import android.graphics.Bitmap
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.fabianospdev.fsplayer.features.player.presentation.components.VideoTimelineWithPreview

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel,
    thumbnailsProvider: suspend (Long) -> Bitmap?
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val player = viewModel.player

    val playerView = remember {
        PlayerView(context).apply {
            useController = false
            this.player = player
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        /* 🎥 Container do vídeo */
        Box(
            modifier = if (state.isFullscreen) {
                Modifier.fillMaxSize()
            } else {
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            }
        ) {

            /** Player */
            AndroidView(
                factory = {
                    playerView.apply { useController = false }
                },
                update = { view ->
                    view.resizeMode = if (state.isFullscreen)
                        AspectRatioFrameLayout.RESIZE_MODE_FILL
                    else
                        AspectRatioFrameLayout.RESIZE_MODE_FIT
                },
                modifier = Modifier
                    .matchParentSize()
                    .pointerInput(Unit) { detectTapGestures { viewModel.toggleControls() } }
            )

            /** Timeline */
            if (state.showTimeline) {
                VideoTimelineWithPreview(
                    player = player,
                    thumbnailsProvider = thumbnailsProvider,
                    onScrubStart = { viewModel.startScrubbing() },
                    onScrubEnd = { viewModel.stopScrubbing() },
                    onThumbnailShowing = { showing -> viewModel.setThumbnailShowing(showing) },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .pointerInput(Unit) { detectTapGestures { viewModel.toggleControls() } }
                )
            }

            /** Controles */
            if (state.controlsVisible) {
                IconButton(
                    onClick = { viewModel.toggleFullscreen(context as? Activity) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = if (state.isFullscreen) Icons.Filled.FullscreenExit else Icons.Filled.Fullscreen,
                        contentDescription = "Fullscreen",
                        tint = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth().fillMaxHeight()
                        .background(Color.Black.copy(alpha = 0.2f))
                        .padding(horizontal = 12.dp, vertical = 56.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { player.seekBack() }) {
                        Icon(Icons.Default.FastRewind, contentDescription = "Rewind", tint = Color.White)
                    }
                    IconButton(onClick = { viewModel.playPause() }) {
                        Icon(
                            imageVector = if (state.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { player.seekForward() }) {
                        Icon(Icons.Default.FastForward, contentDescription = "Forward", tint = Color.White)
                    }
                }
            }
        }

        /** Conteúdo abaixo do player (fora do fullscreen) */
        if (!state.isFullscreen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Vamos procurar novas músicas ?",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Text(
                        "Procure aonde quiser!",
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }

    }
}
