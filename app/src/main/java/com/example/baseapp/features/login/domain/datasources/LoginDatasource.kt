package com.example.baseapp.features.login.domain.datasources

import com.example.baseapp.features.login.data.models.LoginResponseModel

interface LoginDatasource {
    suspend fun getLogin() : Result<LoginResponseModel>
}