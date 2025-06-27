package com.jah.mapeo.modelo

import com.jah.mapeo.contralador.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientPublic {

    private const val BASE_URL = "https://mapeo-backend.onrender.com/"

    val instance: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}
