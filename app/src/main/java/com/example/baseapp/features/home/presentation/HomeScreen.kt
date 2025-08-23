package com.example.baseapp.features.home.presentation

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
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
                is HomeState.HomeLoading -> {
                    //  ShowLoadingComponent()

                }

                is HomeState.HomeIdle -> {
                    HomeContent(
                        navController = navController,
                        name = "Android",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                is HomeState.HomeSuccess -> {
                    HomeContent(
                        navController = navController,
                        name = "Android",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                is HomeState.HomeError -> {
                    // ShowHomeErrorComponent(viewModel, state)
                }

                is HomeState.HomeNoConnection -> {
                    // ShowHomeNoConnection(state)
                }

                is HomeState.HomeTimeoutError -> {
                    // ShowHomeTimeoutError(state)
                }

                is HomeState.HomeUnauthorized -> {
                    // ShowHomeUnauthorized(state)
                }

                is HomeState.HomeValidationError -> {
                    //ShowHomeValidationError(state)
                }

                else -> {
                    //  ShowHomeUnknown()
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    navController: NavHostController,
    name: String,
    modifier: Modifier = Modifier
) {
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
                    text = "Home Screen",
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}