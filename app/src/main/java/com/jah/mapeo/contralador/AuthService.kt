package com.jah.mapeo.contralador

import com.jah.mapeo.modelo.LoginRequest
import com.jah.mapeo.modelo.LoginResponse
import com.jah.mapeo.modelo.RegistroRequest
import com.jah.mapeo.modelo.RegistroResponse
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
