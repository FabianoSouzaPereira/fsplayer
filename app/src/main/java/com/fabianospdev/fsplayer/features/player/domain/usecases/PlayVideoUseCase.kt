package com.fabianospdev.fsplayer.features.player.domain.usecases

import com.fabianospdev.fsplayer.features.player.domain.repository.VideoRepository

class PlayVideoUseCase(
    private val repository: VideoRepository
) {

    operator fun invoke() {
        repository.playPause()
    }
}