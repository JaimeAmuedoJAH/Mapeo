package com.jah.mapeo.modelo

import com.google.gson.annotations.SerializedName

data class RegistroRequest(
    val nombre: String,
    @SerializedName("correo") val email: String,
    val contrasena: String
)

