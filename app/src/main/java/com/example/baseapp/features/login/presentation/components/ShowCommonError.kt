package com.example.baseapp.features.login.presentation.components

import androidx.compose.runtime.Composable
import com.example.baseapp.core.helpers.exceptions.CommonError

@Composable
fun ShowCommonError() {
    CommonError.UnknownError.message
}