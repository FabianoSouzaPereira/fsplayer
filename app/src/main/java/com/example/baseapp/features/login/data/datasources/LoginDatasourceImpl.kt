package com.example.baseapp.features.login.data.datasources

import com.example.baseapp.features.login.data.models.LoginResponseModel
import com.example.baseapp.features.login.data.remote.LoginApiService
import com.example.baseapp.features.login.domain.datasources.LoginDatasource
import javax.inject.Inject

class LoginDatasourceImpl @Inject constructor(
    private val api: LoginApiService
) : LoginDatasource {

    override suspend fun getLogin(): Result<LoginResponseModel> {
        return try {
            val response = api.getLogin()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(Throwable("Authentication error: ${e.message}", e))
        }
    }
}