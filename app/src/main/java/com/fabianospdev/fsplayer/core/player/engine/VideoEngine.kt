package com.fabianospdev.fsplayer.core.player.engine

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.fabianospdev.fsplayer.core.player.engine.QualityManager
import jakarta.inject.Inject

class VideoEngine @Inject constructor(
    private val player: ExoPlayer
) {

    fun load(url: String) {

        val mediaItem = MediaItem.fromUri(url)

        player.setMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

    fun playPause() {

        if (player.isPlaying)
            player.pause()
        else
            player.play()
    }

    fun seek(position: Long) {

        player.seekTo(position)
    }
}