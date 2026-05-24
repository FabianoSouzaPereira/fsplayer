package com.fabianospdev.fsplayer.features.splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.fsplayer.core.routes.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            when (state.value) {
                is SplashState.SplashLoading -> {
                    //  ShowLoadingComponent()

                }

                is SplashState.SplashIdle -> {
                    SplashContent(
                        navController = navController,
                        name = "Android",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                is SplashState.SplashSuccess -> {
                    SplashContent(
                        navController = navController,
                        name = "Android",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                is SplashState.SplashError -> {
                    // ShowSplashErrorComponent(viewModel, state)
                }

                is SplashState.SplashNoConnection -> {
                    // ShowSplashNoConnection(state)
                }

                is SplashState.SplashTimeoutError -> {
                    // ShowSplashTimeoutError(state)
                }

                is SplashState.SplashUnauthorized -> {
                    // ShowSplashUnauthorized(state)
                }

                is SplashState.SplashValidationError -> {
                    //ShowSplashValidationError(state)
                }

                else -> {
                    //  ShowSplashUnknown()
                }

            }
        }
    }
}

@Composable
fun SplashContent(
    navController: NavHostController,
    name: String,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        delay(4000)
        navController.navigate(Routes.LOGIN) {
            popUpTo(Routes.SPLASH) { inclusive = true } // remove a splash do back stack
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "SplashScreen",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}