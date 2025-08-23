package com.fabianospdev.fsplayer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fabianospdev.fsplayer.core.routes.Routes
import com.fabianospdev.fsplayer.features.home.presentation.HomeRoute
import com.fabianospdev.fsplayer.features.login.presentation.LoginRoute
import com.fabianospdev.fsplayer.features.player.presentation.PlayerActivity
import com.fabianospdev.fsplayer.features.settings.presentation.SettingsRoute
import com.fabianospdev.fsplayer.features.splash.presentation.SplashRoute
import com.fabianospdev.fsplayer.ui.theme.BaseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    tonalElevation = 5.dp
                ) {
                    navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME
                    ) {
                        composable(Routes.SPLASH) {
                            SplashRoute(navController = navController)
                        }
                        composable(Routes.LOGIN) {
                            LoginRoute(navController = navController)
                        }
                        composable(Routes.HOME) {
                            // Aqui você pode passar um callback pro HomeRoute
                            HomeRoute(
                                navController = navController,
                                onOpenPlayer = {
                                    // Aqui chamamos a Activity do player
                                    val intent = Intent(this@MainActivity, PlayerActivity::class.java)
                                    startActivity(intent)
                                }
                            )
                        }
                        composable(Routes.SETTINGS) {
                            SettingsRoute(navController = navController)
                        }
                        // ⚠️ Removemos Routes.PLAYER do NavHost
                        // porque o Player é uma Activity separada
                    }
                }
            }
        }
    }
}

