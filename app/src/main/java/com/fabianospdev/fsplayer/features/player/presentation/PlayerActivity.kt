package com.fabianospdev.fsplayer.features.player.presentation

import android.app.PictureInPictureParams
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class PlayerActivity : ComponentActivity() {

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaUrl = intent?.getStringExtra("media_url")
            ?: "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"

        // Inicializa ExoPlayer
        player = ExoPlayer.Builder(this).build().apply {
            val mediaItem = MediaItem.fromUri(mediaUrl)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }

        setContent {
            PlayerScreen(player = player!!)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isInPictureInPictureMode.not()) {
            player?.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    override fun onUserLeaveHint() {
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(Rational(16, 9))
            .build()
        enterPictureInPictureMode(params)
        super.onUserLeaveHint()
    }
}


