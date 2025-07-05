package com.jah.mapeo.modelo.API.Auth

data class LoginResponse(
    val token: String,
    val usuario: UsuarioResponse
)