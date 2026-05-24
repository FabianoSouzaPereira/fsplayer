package com.fabianospdev.fsplayer.features.login.data.remote

import com.fabianospdev.fsplayer.features.login.data.models.LoginResponseModel
import retrofit2.http.GET

interface LoginApiService {
    @GET("login")
    suspend fun getLogin(): LoginResponseModel
}