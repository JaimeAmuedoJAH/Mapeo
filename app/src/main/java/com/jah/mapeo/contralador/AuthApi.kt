package com.jah.mapeo.contralador

import com.jah.mapeo.modelo.API.Auth.LoginRequest
import com.jah.mapeo.modelo.API.Auth.LoginResponse
import com.jah.mapeo.modelo.API.Auth.RegistroRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/auth/register")
    fun registrar(@Body request: RegistroRequest): Call<ResponseBody>
}