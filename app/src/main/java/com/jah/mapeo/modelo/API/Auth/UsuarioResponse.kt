package com.jah.mapeo.modelo.API.Auth

import com.google.gson.annotations.SerializedName

data class UsuarioResponse(
    @SerializedName("usuario_id") val usuario_id: Int,
    val nombre: String,
    val email: String
)
