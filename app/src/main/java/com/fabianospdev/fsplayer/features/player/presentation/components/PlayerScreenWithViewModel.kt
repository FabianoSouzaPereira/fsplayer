package com.fabianospdev.fsplayer.features.player.presentation.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.fabianospdev.fsplayer.features.player.presentation.PlayerScreen
import com.fabianospdev.fsplayer.features.player.presentation.PlayerViewModel

@Composable
fun PlayerScreenWithViewModel(
    player: ExoPlayer,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    PlayerScreen(
        player = player,
        thumbnailsProvider = { posMs -> viewModel.getThumbnail(posMs) }
    )
}
