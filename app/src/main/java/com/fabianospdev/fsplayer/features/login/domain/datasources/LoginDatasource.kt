package com.fabianospdev.fsplayer.features.login.domain.datasources

import com.fabianospdev.fsplayer.features.login.data.models.LoginResponseModel

interface LoginDatasource {
    suspend fun getLogin() : Result<LoginResponseModel>
}