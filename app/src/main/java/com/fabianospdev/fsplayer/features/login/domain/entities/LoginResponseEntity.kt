package com.fabianospdev.fsplayer.features.login.domain.entities

data class LoginResponseEntity(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val token: String
)