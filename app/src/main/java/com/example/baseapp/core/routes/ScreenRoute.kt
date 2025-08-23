package com.example.baseapp.core.routes

open class ScreenRoute(val route: String) {
    object Splash : ScreenRoute("splash")
    object Login : ScreenRoute("login")
    object Home : ScreenRoute("home")
    object Settings : ScreenRoute("settings")
}