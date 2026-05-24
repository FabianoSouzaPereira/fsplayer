package com.fabianospdev.fsplayer.features.splash.presentation

import com.fabianospdev.fsplayer.features.splash.domain.SplashResponseEntity

sealed class SplashState {
    object SplashLoading : SplashState()

    object SplashIdle : SplashState()

    data class SplashSuccess(val response: SplashResponseEntity) : SplashState()

    data class SplashError(val error: String) : SplashState() {
        fun isNetworkRelated(): Boolean {
            return error.contains("network", ignoreCase = true)
        }
    }

    data class SplashNoConnection(val errorMessage: String) : SplashState()

    data class SplashValidationError(val message: String) : SplashState()

    data class SplashTimeoutError(val message: String) : SplashState()

    data class SplashUnauthorized(val message: String) : SplashState()

    data class SplashUnknown(val message: String) : SplashState() {
        fun isSplashUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}