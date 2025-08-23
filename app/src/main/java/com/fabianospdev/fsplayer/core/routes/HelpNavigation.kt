package com.fabianospdev.fsplayer.core.routes

import androidx.navigation.NavHostController

fun navigateToSplash(navController: NavHostController) {
    navController.navigate(Routes.SPLASH) {
        popUpTo(Routes.SPLASH) { inclusive = true }
    }
}

fun navigateToLogin(navController: NavHostController) {
    navController.navigate(Routes.LOGIN) {
        popUpTo(Routes.LOGIN) { inclusive = true }
    }
}

fun navigateToHome(navController: NavHostController) {
    navController.navigate(Routes.HOME) {
        popUpTo(Routes.HOME) { inclusive = true }
    }
}

fun navigateToSettings(navController: NavHostController) {
    navController.navigate(Routes.SETTINGS) {
        popUpTo(Routes.SETTINGS) { inclusive = true }
    }
}
