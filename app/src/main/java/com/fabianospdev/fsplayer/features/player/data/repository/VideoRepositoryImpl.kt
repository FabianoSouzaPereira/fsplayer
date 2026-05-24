package com.fabianospdev.fsplayer.features.player.data.repository

import com.fabianospdev.fsplayer.core.player.engine.VideoEngine
import com.fabianospdev.fsplayer.features.player.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoEngine: VideoEngine
) : VideoRepository {

    override fun load(url: String) {
        videoEngine.load(url)
    }

    override fun playPause() {
        videoEngine.playPause()
    }

    override fun seek(position: Long) {
        videoEngine.seek(position)
    }
}