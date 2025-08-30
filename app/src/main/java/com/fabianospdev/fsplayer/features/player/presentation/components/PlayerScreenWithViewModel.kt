package com.fabianospdev.fsplayer.features.player.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.fabianospdev.fsplayer.features.player.presentation.PlayerScreen
import com.fabianospdev.fsplayer.features.player.presentation.PlayerViewModel

@Composable
fun PlayerScreenWithViewModel(
    mediaUrl: String,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    DisposableEffect(Unit) {
        onDispose {
            println("PlayerScreenWithViewModel: onDispose")
            viewModel.onStop()
        }
    }

    LaunchedEffect(mediaUrl) {
        if (viewModel.videoUrl != mediaUrl) {
            viewModel.prepare(mediaUrl)
        }
    }

    PlayerScreen(
        viewModel = viewModel,
        thumbnailsProvider = { posMs -> viewModel.getThumbnail(posMs) }
    )
}
