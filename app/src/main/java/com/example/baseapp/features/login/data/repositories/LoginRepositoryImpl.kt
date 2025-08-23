package com.example.baseapp.features.login.data.repositories

import com.example.baseapp.features.login.data.models.toEntity
import com.example.baseapp.features.login.domain.datasources.LoginDatasource
import com.example.baseapp.features.login.domain.entities.LoginResponseEntity
import com.example.baseapp.features.login.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDatasource: LoginDatasource
) : LoginRepository {

    override suspend fun getLogin(): Result<LoginResponseEntity> {
        return try {
            val responseModel = loginDatasource.getLogin().getOrThrow()
            Result.success(responseModel.toEntity())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
