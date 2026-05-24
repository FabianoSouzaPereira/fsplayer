package com.fabianospdev.fsplayer.features.splash.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<SplashState>(SplashState.SplashIdle)
    val state: StateFlow<SplashState> = _state
}