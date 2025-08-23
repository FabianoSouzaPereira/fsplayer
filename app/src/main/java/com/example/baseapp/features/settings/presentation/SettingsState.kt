package com.example.baseapp.features.settings.presentation

import com.example.baseapp.features.settings.domain.entities.SettingsResponseEntity


sealed class SettingsState {
    object SettingsLoading : SettingsState()

    object SettingsIdle : SettingsState()

    data class SettingsSuccess(val response: SettingsResponseEntity) : SettingsState()

    data class SettingsError(val error: String) : SettingsState() {
        fun isNetworkRelated(): Boolean {
            return error.contains("network", ignoreCase = true)
        }
    }

    data class SettingsNoConnection(val errorMessage: String) : SettingsState()

    data class SettingsValidationError(val message: String) : SettingsState()

    data class SettingsTimeoutError(val message: String) : SettingsState()

    data class SettingsUnauthorized(val message: String) : SettingsState()

    data class SettingsUnknown(val message: String) : SettingsState() {
        fun isSettingsUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}