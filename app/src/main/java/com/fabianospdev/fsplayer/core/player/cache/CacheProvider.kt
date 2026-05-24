package com.fabianospdev.fsplayer.core.player.cache

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import java.io.File

object CacheProvider {

    @OptIn(UnstableApi::class)
    fun create(context: Context): SimpleCache {

        return SimpleCache(
            File(context.cacheDir, "media_cache"),
            LeastRecentlyUsedCacheEvictor(500L * 1024L * 1024L),
            StandaloneDatabaseProvider(context)
        )
    }
}