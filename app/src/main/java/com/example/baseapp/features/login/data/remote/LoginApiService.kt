package com.example.baseapp.features.login.data.remote

import com.example.baseapp.features.login.data.models.LoginResponseModel
import retrofit2.http.GET

interface LoginApiService {
    @GET("login")
    suspend fun getLogin(): LoginResponseModel
}