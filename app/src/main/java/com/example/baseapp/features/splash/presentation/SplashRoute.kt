package com.example.baseapp.features.splash.presentation


import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SplashRoute(navController: NavHostController) {
    val viewModel: SplashScreenViewModel = hiltViewModel()
    SplashScreen(
        viewModel = viewModel,
        navController = navController
    )
}