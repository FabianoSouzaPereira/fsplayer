package com.fabianospdev.fsplayer.core.helpers.retry

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DefaultRetryController(private val maxRetries: Int = 3) : RetryController {
    private val _isRetryEnabled = MutableStateFlow(true)
    override val isRetryEnabled: StateFlow<Boolean> = _isRetryEnabled

    private val _isRetryLimitReached = MutableStateFlow(false)
    override val isRetryLimitReached: StateFlow<Boolean> get() = _isRetryLimitReached

    private var retryCount = 0

    override fun incrementRetryCount() {
        retryCount++
        if (retryCount >= maxRetries) {
            _isRetryEnabled.value = false
        }
    }

    override fun resetRetryCount() {
        retryCount = 0
        _isRetryEnabled.value = true
    }

    override fun resetRetryLimitNotification() {
        retryCount = 0
        _isRetryLimitReached.value = true
    }
}
