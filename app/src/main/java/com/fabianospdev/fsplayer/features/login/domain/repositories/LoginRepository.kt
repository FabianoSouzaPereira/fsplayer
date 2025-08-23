package com.fabianospdev.fsplayer.features.login.domain.repositories

import com.fabianospdev.fsplayer.features.login.domain.entities.LoginResponseEntity

interface LoginRepository {
    suspend fun getLogin(): Result<LoginResponseEntity>
}