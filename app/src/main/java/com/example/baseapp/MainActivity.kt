package com.example.baseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baseapp.core.routes.Routes
import com.example.baseapp.features.home.presentation.HomeRoute
import com.example.baseapp.features.login.presentation.LoginRoute
import com.example.baseapp.features.settings.presentation.SettingsRoute
import com.example.baseapp.features.splash.presentation.SplashRoute
import com.example.baseapp.ui.theme.BaseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                tonalElevation = 5.dp
            ) {
                BaseAppTheme {
                    navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.SPLASH) {
                        composable(Routes.SPLASH) {
                            SplashRoute(navController = navController)
                        }
                        composable(Routes.LOGIN) {
                            LoginRoute(navController = navController)
                        }
                        composable(Routes.HOME) {
                            HomeRoute(navController = navController)
                        }
                        composable(Routes.SETTINGS) {
                            SettingsRoute(navController = navController)
                        }
                    }
                }
            }
        }
    }
}