package com.fabianospdev.fsplayer.features.player.presentation

import android.app.PictureInPictureParams
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.fabianospdev.fsplayer.features.player.presentation.components.PlayerScreenWithViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerActivity : ComponentActivity() {

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaUrl = intent?.getStringExtra("media_url")
            ?: "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

        player = ExoPlayer.Builder(this).build().apply {
            setMediaItem(MediaItem.fromUri(mediaUrl))
            prepare()
            playWhenReady = true
        }

        setContent {
            PlayerScreenWithViewModel(player = player!!)
        }
    }

    override fun onStop() {
        super.onStop()
        if (!isInPictureInPictureMode) {
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
