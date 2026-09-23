package com.fabianospdev.fsplayer.features.player.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoTimelineWithPreview(
    player: ExoPlayer,
    thumbnailsProvider: suspend (Long) -> Bitmap?,
    modifier: Modifier = Modifier,
    onScrubStart: () -> Unit = {},
    onScrubEnd: () -> Unit = {},
    onThumbnailShowing: (Boolean) -> Unit = {}
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    var dragPosition by remember { mutableStateOf<Float?>(null) }
    var duration by remember { mutableStateOf(1f) }
    var currentThumb by remember { mutableStateOf<Bitmap?>(null) }

    /** Atualiza duração */
    LaunchedEffect(player) {
        while (duration <= 1f) {
            val d = player.duration
            if (d > 0 && d != Long.MIN_VALUE) duration = d.toFloat()
            delay(200)
        }
    }

    /** Atualiza posição do player */
    LaunchedEffect(player) {
        while (true) {
            if (dragPosition == null) {
                sliderPosition = player.currentPosition.toFloat()
            }
            delay(200)
        }
    }

    /** Atualiza thumbnail enquanto arrasta (com debounce + normalização p/ 1s) */
    dragPosition?.let { pos ->
        LaunchedEffect(key1 = pos.toLong() / 1000) {
            onScrubStart()
            onThumbnailShowing(true) // thumbnail prestes a ser exibido
            delay(200)
            val normalizedPos = (pos.toLong() / 1000) * 1000
            currentThumb = withContext(Dispatchers.IO) {
                thumbnailsProvider(normalizedPos)
            }
        }
    }

    Box(modifier = modifier) {
        Slider(
            value = dragPosition ?: sliderPosition,
            onValueChange = { newPos ->
                if (dragPosition == null) onScrubStart()
                dragPosition = newPos
            },
            onValueChangeFinished = {
                dragPosition?.let { pos ->
                    player.seekTo(pos.toLong())
                    sliderPosition = pos
                }
                dragPosition = null
                currentThumb = null
                onThumbnailShowing(false)
                onScrubEnd()
            },
            valueRange = 0f..duration,
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Red,
                inactiveTrackColor = Color.LightGray
            ),
            thumb = {
                Box(
                    modifier = Modifier
                        .offset(y = 2.dp)
                        .size(14.dp)
                        .clip(CircleShape)
                        . background(Color.Red)
                )
            },
            track = { sliderState ->
                val fraction = (
                        (sliderState.value - sliderState.valueRange.start) /
                        (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
                ).coerceIn(0f, 1f)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.LightGray)
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)
                            .fillMaxHeight()
                            .background(Color.Red)
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )

        currentThumb?.let { thumb ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-50).dp)
                    .background(Color.Black.copy(alpha = 0.7f), shape = RoundedCornerShape(size = 6.dp))
                    .padding(all = 2.dp)
            ) {
                Image(
                    bitmap = thumb.asImageBitmap(),
                    contentDescription = "Thumb",
                    modifier = Modifier
                        .size(width = 140.dp, height = 80.dp)
                        .clip(RoundedCornerShape(6.dp))
                )

                Text(
                    text = formatTime(sliderPosition.toLong()), // ex: 01:23
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(4.dp)
                )
            }
        } ?: run {
            // Mostra tempo mesmo sem thumbnail
            Text(
                text = formatTime(dragPosition?.toLong() ?: sliderPosition.toLong()),
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-40).dp)
                    .background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}

fun formatTime(ms: Long): String {
    val totalSec = ms / 1000
    val minutes = totalSec / 60
    val seconds = totalSec % 60
    return "%02d:%02d".format(minutes, seconds)
}
