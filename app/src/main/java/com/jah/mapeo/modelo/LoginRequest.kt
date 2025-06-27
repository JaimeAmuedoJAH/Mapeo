package com.jah.mapeo.modelo

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("correo") val email: String,
    val contrasena: String
)



