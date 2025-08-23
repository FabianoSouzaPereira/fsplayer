package com.example.baseapp.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeRoute(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()

    HomeScreen(
        viewModel = viewModel,
        navController = navController
    )
}