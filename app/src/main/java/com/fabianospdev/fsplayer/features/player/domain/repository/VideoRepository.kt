package com.fabianospdev.fsplayer.features.player.domain.repository

interface VideoRepository {
    fun load(url: String)

    fun playPause()

    fun seek(position: Long)
}