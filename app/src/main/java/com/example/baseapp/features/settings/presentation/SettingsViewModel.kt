package com.example.baseapp.features.settings.presentation

import androidx.lifecycle.ViewModel
import com.example.baseapp.features.login.presentation.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<SettingsState>(SettingsState.SettingsIdle)
    val state: StateFlow<SettingsState> = _state
}
