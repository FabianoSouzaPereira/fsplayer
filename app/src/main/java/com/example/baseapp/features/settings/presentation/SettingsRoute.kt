package com.example.baseapp.features.settings.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SettingsRoute(navController: NavHostController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    SettingsRoute(
        navController = navController
    )
}