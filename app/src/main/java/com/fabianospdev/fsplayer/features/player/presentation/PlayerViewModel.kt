package com.fabianospdev.fsplayer.features.player.presentation

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    // ✅ Estado do player
    private val _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state

    private var hideJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    private val thumbnailCache = mutableMapOf<Long, Bitmap?>()
    var videoUrl: String = ""
    var lastPosition: Long = 0L

    val player: ExoPlayer by lazy {
        ExoPlayer.Builder(appContext).build().apply {
            playWhenReady = false
        }
    }

    init {
        showControlsTemporarily()
    }

    /** Prepara o player com o URL, mantendo a última posição se houver */
    fun prepare(url: String) {
        if (videoUrl != url) {
            videoUrl = url
            player.setMediaItem(MediaItem.fromUri(url))
            player.prepare()
        }

        // Sempre seek para a última posição
        player.seekTo(lastPosition)
        player.playWhenReady = true
        _state.value = _state.value.copy(isPlaying = true)
    }

    /** Alterna play/pause */
    fun playPause() {
        if (player.isPlaying) player.pause() else player.play()
        _state.value = _state.value.copy(isPlaying = player.isPlaying)
        showControlsTemporarily()
    }

    /** Salva posição e pausa ao sair da tela */
    fun onStop() {
        lastPosition = player.currentPosition
        player.pause()
    }

    /** Alterna fullscreen e orientação da tela */
    fun toggleFullscreen(activity: Activity?) {
        val newFullscreen = !_state.value.isFullscreen
        activity?.requestedOrientation = if (newFullscreen)
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        _state.value = _state.value.copy(isFullscreen = newFullscreen)
        showControlsTemporarily()
    }

    /** Mostra ou esconde controles manualmente */
    fun toggleControls() {
        if (_state.value.controlsVisible) {
            _state.value = _state.value.copy(controlsVisible = false, showTimeline = false)
        } else {
            showControlsTemporarily()
        }
    }

    /** Inicia scrubbing na timeline */
    fun startScrubbing() {
        _state.value = _state.value.copy(isSeeking = true, controlsVisible = false, showTimeline = true)
        hideJob?.cancel()
    }

    /** Para scrubbing */
    fun stopScrubbing() {
        _state.value = _state.value.copy(isSeeking = false)
        showControlsTemporarily()
    }

    /** Mostra controles temporariamente por 3s, mas só conta se não estiver arrastando */
    private fun showControlsTemporarily() {
        _state.value = _state.value.copy(controlsVisible = true, showTimeline = true)
        hideJob?.cancel()
        hideJob = scope.launch {
            var elapsed = 0L
            val interval = 200L
            while (elapsed < 3000L) {
                if (!_state.value.isSeeking && !_state.value.isShowingThumbnail) {
                    elapsed += interval
                }
                delay(interval)
            }
            if (!_state.value.isSeeking && !_state.value.isShowingThumbnail) {
                _state.value = _state.value.copy(isShowingThumbnail = false)
                _state.value = _state.value.copy(controlsVisible = false, showTimeline = false)
            }
        }
    }


    /** Gera thumbnails usando Glide */
    suspend fun getThumbnail(positionMs: Long): Bitmap? {
        val key = (positionMs / 1000) * 1000 // arredonda para 1s
        thumbnailCache[key]?.let { return it }

        return try {
            val futureTarget = Glide.with(appContext)
                .asBitmap()
                .load(videoUrl)
                .frame(positionMs * 1000L) // micros
                .submit()

            val bmp = futureTarget.get()
            thumbnailCache[key] = bmp
            bmp
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun setThumbnailShowing(showing: Boolean) {
        _state.value = _state.value.copy(isShowingThumbnail = showing)
    }



    /** Libera recursos ao destruir ViewModel */
    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}
