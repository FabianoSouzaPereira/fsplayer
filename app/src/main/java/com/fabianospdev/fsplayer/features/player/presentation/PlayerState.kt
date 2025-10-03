package com.fabianospdev.fsplayer.features.player.presentation

data class PlayerState(
    val isPlaying: Boolean = false,
    val isFullscreen: Boolean = false,
    val controlsVisible: Boolean = true,
    val showTimeline: Boolean = true,
    val isSeeking: Boolean = false,
    val isShowingThumbnail: Boolean = false
)

