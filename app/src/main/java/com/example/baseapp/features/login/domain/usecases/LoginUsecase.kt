package com.example.baseapp.features.login.domain.usecases

import com.example.baseapp.features.login.domain.entities.LoginResponseEntity

interface LoginUsecase {
    suspend fun getLogin() : Result<LoginResponseEntity>
}