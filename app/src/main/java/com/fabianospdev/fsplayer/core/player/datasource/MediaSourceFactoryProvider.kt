package com.fabianospdev.fsplayer.core.player.datasource

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.exoplayer.source.MediaSourceFactory

@OptIn(UnstableApi::class)
class MediaSourceFactoryProvider(
    private val context: Context,
    private val cache: Cache
) {

    @OptIn(UnstableApi::class)
    fun create(): MediaSourceFactory {

        val httpFactory = DefaultHttpDataSource.Factory()
            .setUserAgent(DefaultUserAgent.value)
            .setAllowCrossProtocolRedirects(true)

        val cacheDataSourceFactory = CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(httpFactory)

        return DefaultMediaSourceFactory(cacheDataSourceFactory)
    }
}