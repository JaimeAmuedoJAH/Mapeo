package com.jah.mapeo.modelo

import android.content.Context
import com.jah.mapeo.contralador.AuthApi
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {

    private const val BASE_URL = "https://mapeo-backend.onrender.com/" // cámbialo por tu URL real
    private var appContext: Context? = null

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private fun getToken(): String? {
        val prefs = appContext?.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return prefs?.getString("jwt_token", null)
    }

    private val authInterceptor = Interceptor { chain ->
        val token = getToken()
        val requestBuilder = chain.request().newBuilder()

        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        chain.proceed(requestBuilder.build())
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor) // <- aquí agregas el interceptor con el token
        .build()

    val instance: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // ✅ aquí conectas el cliente con el interceptor del token
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

}

