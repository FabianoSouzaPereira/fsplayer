package com.fabianospdev.fsplayer.features.player.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun VideoTimelineWithPreview(
    player: ExoPlayer,
    thumbnailsProvider: suspend (Long) -> Bitmap?,
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    var dragPosition by remember { mutableStateOf<Float?>(null) }
    var duration by remember { mutableStateOf(1f) }
    var currentThumb by remember { mutableStateOf<Bitmap?>(null) }

    // Atualiza duração quando player fica pronto
    LaunchedEffect(player) {
        while (duration <= 1f) {
            val d = player.duration
            if (d > 0 && d != Long.MIN_VALUE) {
                duration = d.toFloat()
            }
            kotlinx.coroutines.delay(300)
        }
    }

    // Atualiza posição enquanto toca (se não estiver arrastando)
    LaunchedEffect(player) {
        while (true) {
            if (dragPosition == null) {
                sliderPosition = player.currentPosition.toFloat()
            }
            kotlinx.coroutines.delay(500)
        }
    }

    // Atualiza thumbnail só durante drag
    LaunchedEffect(dragPosition) {
        dragPosition?.let { pos ->
            currentThumb = withContext(Dispatchers.IO) {
                thumbnailsProvider(pos.toLong())
            }
        }
    }

    Box(modifier = modifier) {
        Slider(
            value = dragPosition ?: sliderPosition,
            onValueChange = { newPos ->
                dragPosition = newPos
            },
            onValueChangeFinished = {
                dragPosition?.let { pos ->
                    player.seekTo(pos.toLong())
                    sliderPosition = pos
                }
                dragPosition = null
            },
            valueRange = 0f..duration
        )

        currentThumb?.let { thumb ->
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-70).dp)
            ) {
                Image(
                    bitmap = thumb.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp, 70.dp)
                )
            }
        }
    }
}
