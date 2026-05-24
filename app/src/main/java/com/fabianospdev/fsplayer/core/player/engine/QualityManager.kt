package com.fabianospdev.fsplayer.core.player.engine

import androidx.annotation.OptIn
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import jakarta.inject.Inject

class QualityManager @Inject constructor(private val player: ExoPlayer) {

    @OptIn(UnstableApi::class)
    fun getAvailableQualities(): List<Pair<Int, Int>> {

        val result = mutableListOf<Pair<Int, Int>>()

        val tracks = player.currentTracks.groups

        for (group in tracks) {

            if (group.type == C.TRACK_TYPE_VIDEO) {

                for (i in 0 until group.length) {

                    val format = group.getTrackFormat(i)

                    result.add(
                        format.height to format.bitrate
                    )
                }
            }
        }

        return result
    }

    fun setMaxQuality(height: Int) {

        player.trackSelectionParameters =
            player.trackSelectionParameters
                .buildUpon()
                .setMaxVideoSize(height, height * 16 / 9)
                .build()
    }
}