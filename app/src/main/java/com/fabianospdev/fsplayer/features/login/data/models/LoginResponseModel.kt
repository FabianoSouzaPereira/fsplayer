package com.fabianospdev.fsplayer.features.login.data.models

import com.fabianospdev.fsplayer.features.login.domain.entities.LoginResponseEntity

data class LoginResponseModel(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val token: String
)

// Mapper
fun LoginResponseModel.toEntity(): LoginResponseEntity {
    return LoginResponseEntity(
        id = id,
        name = name,
        email = email,
        password = password,
        token = token
    )
}