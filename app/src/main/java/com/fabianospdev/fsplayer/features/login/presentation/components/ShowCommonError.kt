package com.fabianospdev.fsplayer.features.login.presentation.components

import androidx.compose.runtime.Composable
import com.fabianospdev.fsplayer.core.helpers.exceptions.CommonError

@Composable
fun ShowCommonError() {
    CommonError.UnknownError.message
}