package com.fabianospdev.fsplayer.core.player.thumbnail

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class ThumbnailProvider @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    suspend fun getThumbnail(
        url: String,
        positionMs: Long
    ): Bitmap? {

        return Glide.with(context)
            .asBitmap()
            .load(url)
            .frame(positionMs * 1000)
            .submit()
            .get()
    }
}