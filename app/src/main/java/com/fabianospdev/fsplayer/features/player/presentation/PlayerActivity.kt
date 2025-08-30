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
            ?: "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

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
