package com.example.baseapp.features.login.domain.usecases

import com.example.baseapp.features.login.domain.entities.LoginResponseEntity
import com.example.baseapp.features.login.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginUsecaseImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUsecase {

    override suspend fun getLogin(): Result<LoginResponseEntity> {
        return repository.getLogin()
    }
}