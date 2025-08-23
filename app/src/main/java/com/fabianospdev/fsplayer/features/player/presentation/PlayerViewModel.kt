package com.fabianospdev.fsplayer.features.player.presentation

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    var videoUrl: String = ""

    private val thumbnailCache = mutableMapOf<Long, Bitmap?>()

    suspend fun getThumbnail(positionMs: Long): Bitmap? {
        thumbnailCache[positionMs]?.let { return it }

        return try {
            val futureTarget = Glide.with(appContext)
                .asBitmap()
                .load(videoUrl)
                .frame(positionMs * 1000) // micros
                .submit()

            val bmp = futureTarget.get()
            thumbnailCache[positionMs] = bmp
            bmp
        } catch (e: Exception) {
            null
        }
    }
}

