package com.fabianospdev.fsplayer.features.player.presentation

import android.app.PictureInPictureParams
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fabianospdev.fsplayer.features.player.presentation.components.PlayerScreenWithViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mediaUrl = intent?.getStringExtra("media_url")
            ?: "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"

        setContent {
            PlayerScreenWithViewModel(mediaUrl = mediaUrl)
        }
    }

    override fun onUserLeaveHint() {
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(Rational(16, 9))
            .build()
        enterPictureInPictureMode(params)
        super.onUserLeaveHint()
    }
}
