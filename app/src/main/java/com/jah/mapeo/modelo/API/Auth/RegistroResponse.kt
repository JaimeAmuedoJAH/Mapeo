package com.jah.mapeo.modelo.API.Auth

data class RegistroResponse(
    val id: Int,
    val nombre: String,
    val correo: String,
    val token: String // solo si el backend lo devuelve inmediatamente tras registrar
)