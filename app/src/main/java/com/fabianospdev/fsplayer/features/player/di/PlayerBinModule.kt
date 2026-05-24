package com.fabianospdev.fsplayer.features.player.di

import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.fabianospdev.fsplayer.features.player.data.repository.VideoRepositoryImpl
import com.fabianospdev.fsplayer.features.player.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @OptIn(UnstableApi::class)
    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer {

        val userAgent =
            "Mozilla/5.0 (Linux; Android 14; SM-S918B Build/UP1A.231005.007) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/125.0.0.0 Mobile Safari/537.36"

        val httpDataSourceFactory =
            DefaultHttpDataSource.Factory()
                .setUserAgent(userAgent)
                .setAllowCrossProtocolRedirects(true)

        val dataSourceFactory =
            DefaultDataSource.Factory(
                context,
                httpDataSourceFactory
            )

        return ExoPlayer.Builder(context)
            .setMediaSourceFactory(
                DefaultMediaSourceFactory(dataSourceFactory)
            )
            .build()
            .apply {

                playWhenReady = true

                addListener(object : Player.Listener {

                    override fun onPlayerError(error: PlaybackException) {

                        Log.e(
                            "FSPLAYER",
                            "Player error: ${error.errorCodeName}",
                            error
                        )
                    }

                    override fun onPlaybackStateChanged(state: Int) {

                        when (state) {

                            Player.STATE_IDLE ->
                                Log.d("FSPLAYER", "STATE_IDLE")

                            Player.STATE_BUFFERING ->
                                Log.d("FSPLAYER", "STATE_BUFFERING")

                            Player.STATE_READY ->
                                Log.d("FSPLAYER", "STATE_READY")

                            Player.STATE_ENDED ->
                                Log.d("FSPLAYER", "STATE_ENDED")
                        }
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {

                        Log.d(
                            "FSPLAYER",
                            "isPlaying: $isPlaying"
                        )
                    }
                })
            }
    }
}