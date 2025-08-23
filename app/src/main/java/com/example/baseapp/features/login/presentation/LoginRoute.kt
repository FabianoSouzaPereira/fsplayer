package com.example.baseapp.features.login.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun LoginRoute(navController: NavHostController) {
    val viewModel: LoginViewModel = hiltViewModel()

    LoginScreen(
        viewModel = viewModel,
        navController = navController
    )
}