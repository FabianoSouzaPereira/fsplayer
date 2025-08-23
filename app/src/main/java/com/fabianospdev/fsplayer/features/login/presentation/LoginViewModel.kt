package com.fabianospdev.fsplayer.features.login.presentation

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.fsplayer.core.helpers.retry.RetryController
import com.fabianospdev.fsplayer.features.login.domain.usecases.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUsecase,
    private val retryController: RetryController,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.LoginIdle)
    val state: StateFlow<LoginState> = _state

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    /** Field validation (calculated in a derived way) **/
    var isUserNameEmpty = derivedStateOf { username.value.isEmpty() }
    var isPasswordEmpty = derivedStateOf { password.value.isEmpty() }
    var isFormValid = derivedStateOf { username.value.isNotEmpty() && password.value.isNotEmpty() }

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached


    fun performLogin(email: String, password: String) {
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }

        if (email.isBlank() || password.isBlank()) {
            _state.value = LoginState.LoginValidationError("E-mail e senha são obrigatórios")
            return
        }

        _state.value = LoginState.LoginLoading

        viewModelScope.launch {
            _state.value = LoginState.LoginLoading

            val result = loginUsecase.getLogin()

            _state.value = result.fold(
                onSuccess = { response ->
                    retryController.resetRetryCount()
                    LoginState.LoginSuccess(response)
                },
                onFailure = { throwable ->
                    retryController.incrementRetryCount()
                    val message = throwable.message ?: "Erro desconhecido"

                    when {
                        message.contains("timeout", ignoreCase = true) ->
                            LoginState.LoginTimeoutError(message)

                        message.contains("unauthorized", ignoreCase = true) ||
                                message.contains("401") ->
                            LoginState.LoginUnauthorized(message)

                        message.contains("network", ignoreCase = true) ||
                                message.contains("unable to resolve host", ignoreCase = true) ->
                            LoginState.LoginNoConnection(message)

                        else -> LoginState.LoginUnknown(message)
                    }
                }
            )
        }
    }

    fun resetState() {
        _state.value = LoginState.LoginIdle
    }

    fun clearInputFields() {
        username.value = ""
        password.value = ""
    }
}