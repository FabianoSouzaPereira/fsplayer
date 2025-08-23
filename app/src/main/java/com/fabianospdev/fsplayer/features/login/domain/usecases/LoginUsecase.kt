package com.fabianospdev.fsplayer.features.login.domain.usecases

import com.fabianospdev.fsplayer.features.login.domain.entities.LoginResponseEntity

interface LoginUsecase {
    suspend fun getLogin() : Result<LoginResponseEntity>
}