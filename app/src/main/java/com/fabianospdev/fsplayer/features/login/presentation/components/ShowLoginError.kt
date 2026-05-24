package com.fabianospdev.fsplayer.features.login.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fabianospdev.fsplayer.core.helpers.exceptions.CommonError
import com.fabianospdev.fsplayer.features.login.presentation.LoginError
import com.fabianospdev.fsplayer.features.login.presentation.LoginState

@Composable
fun ShowLoginError(state: LoginState) {
    val errorMessage = when ((state as LoginState.LoginError).error) {
        LoginState.LoginError(state.error).userNotFound().toString() -> LoginError.UserNotFound.message
        LoginState.LoginError(state.error).loginFailed().toString() -> LoginError.LoginFailed.message
        else -> CommonError.UnknownError.message
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("ERROR: $errorMessage")
    }
}