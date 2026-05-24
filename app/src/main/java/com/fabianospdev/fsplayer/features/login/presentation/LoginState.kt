package com.fabianospdev.fsplayer.features.login.presentation

import com.fabianospdev.fsplayer.features.login.domain.entities.LoginResponseEntity

sealed class LoginState {
    object LoginLoading : LoginState()

    object LoginIdle : LoginState()

    data class LoginSuccess(val response: LoginResponseEntity) : LoginState()

    data class LoginError(val error: String) : LoginState() {
        fun isNetworkRelated(): Boolean {
            return error.contains("network", ignoreCase = true)
        }

        fun userNotFound(): Boolean {
            return error.contains("User not found.")
        }

        fun loginFailed(): Boolean {
            return error.contains("Login failed. Please try again.")
        }
    }

    data class LoginNoConnection(val errorMessage: String) : LoginState()

    data class LoginValidationError(val message: String) : LoginState()

    data class LoginTimeoutError(val message: String) : LoginState()

    data class LoginUnauthorized(val message: String) : LoginState()

    data class LoginUnknown(val message: String) : LoginState() {
        fun isLoginUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}