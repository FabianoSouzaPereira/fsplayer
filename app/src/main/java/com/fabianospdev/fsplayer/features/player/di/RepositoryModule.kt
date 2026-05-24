package com.fabianospdev.fsplayer.features.player.di

import com.fabianospdev.fsplayer.features.player.data.repository.VideoRepositoryImpl
import com.fabianospdev.fsplayer.features.player.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindVideoRepository(
        impl: VideoRepositoryImpl
    ): VideoRepository
}