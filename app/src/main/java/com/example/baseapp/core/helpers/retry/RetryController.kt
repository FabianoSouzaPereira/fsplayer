package com.example.baseapp.core.helpers.retry

import kotlinx.coroutines.flow.StateFlow

interface RetryController {
    val isRetryEnabled: StateFlow<Boolean>
    val isRetryLimitReached: StateFlow<Boolean>

    fun incrementRetryCount()
    fun resetRetryCount()
    fun resetRetryLimitNotification()
}