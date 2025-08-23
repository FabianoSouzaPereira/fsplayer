package com.example.baseapp.features.login.domain.repositories

import com.example.baseapp.features.login.domain.entities.LoginResponseEntity

interface LoginRepository {
    suspend fun getLogin(): Result<LoginResponseEntity>
}