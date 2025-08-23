package com.example.baseapp.features.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.baseapp.features.login.presentation.components.ShowCommonError
import com.example.baseapp.features.login.presentation.components.ShowLoginError
import com.example.baseapp.features.login.presentation.components.ShowLoginLoading
import com.example.baseapp.features.login.presentation.components.ShowLoginNoConnection
import com.example.baseapp.features.login.presentation.components.ShowLoginTimeoutError
import com.example.baseapp.features.login.presentation.components.ShowLoginUnauthorized
import com.example.baseapp.features.login.presentation.components.ShowLoginValidationError
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
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
                is LoginState.LoginLoading -> {
                    ShowLoginLoading()
                }

                is LoginState.LoginIdle -> {
                    LoginContent(
                        navController = navController,
                        name = "Android",
                        viewModel = viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                is LoginState.LoginSuccess -> {
                    LoginContent(
                        navController = navController,
                        name = "Android",
                        viewModel = viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                is LoginState.LoginError -> {
                    ShowLoginError(state.value)
                }

                is LoginState.LoginNoConnection -> {
                    ShowLoginNoConnection()
                }

                is LoginState.LoginTimeoutError -> {
                    ShowLoginTimeoutError()
                }

                is LoginState.LoginUnauthorized -> {
                    ShowLoginUnauthorized()
                }

                is LoginState.LoginValidationError -> {
                    ShowLoginValidationError()
                }

                else -> {
                    ShowCommonError()
                }
            }
        }
    }
}

@Composable
fun LoginContent(
    navController: NavHostController,
    name: String,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login Screen",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 📧 Campo de e-mail
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // 🔒 Campo de senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // ✅ Botão de login
        Button(
            onClick = {
                scope.launch {
                    viewModel.performLogin(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fazer login")
        }
    }
}