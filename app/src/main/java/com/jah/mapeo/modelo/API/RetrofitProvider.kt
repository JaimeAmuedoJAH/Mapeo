package com.jah.mapeo.modelo.API

import android.annotation.SuppressLint
import android.content.Context
import com.jah.mapeo.contralador.ViajeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StaticFieldLeak")
object RetrofitProvider {
    private const val BASE_URL = "https://mapeo-backend.onrender.com/" // ✅ Sin espacios

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private fun getToken(): String? {
        val prefs = appContext.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return prefs.getString("jwt_token", null)
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, java.util.concurrent.TimeUnit.SECONDS) // Tiempo para establecer conexión
            .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)    // Tiempo para esperar respuesta
            .writeTimeout(20, java.util.concurrent.TimeUnit.SECONDS)   // Tiempo para enviar cuerpo de solicitud
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val token = getToken()
                val request = chain.request().newBuilder().apply {
                    if (!token.isNullOrEmpty()) {
                        addHeader("Authorization", "Bearer $token")
                    }
                }.build()
                chain.proceed(request)
            }
            .build()
    }


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T = retrofit.create(service)

    val viajeApi: ViajeService by lazy {
        create(ViajeService::class.java)
    }
}