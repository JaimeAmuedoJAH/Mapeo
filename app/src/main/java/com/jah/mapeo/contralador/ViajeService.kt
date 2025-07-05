package com.jah.mapeo.contralador

import com.jah.mapeo.modelo.API.Viajes.ViajeRequestDTO
import com.jah.mapeo.modelo.API.Viajes.ViajeResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ViajeService {

    @POST("api/viajes")
    suspend fun crearViaje(@Body request: ViajeRequestDTO): Response<ViajeResponseDTO>

    @GET("api/viajes/{id}")
    suspend fun obtenerViaje(@Path("id") viajeId: Int): Response<ViajeResponseDTO>

    @GET("api/viajes/usuario/{usuarioId}")
    suspend fun obtenerViajesPorUsuario(@Path("usuarioId") usuarioId: Int): Response<List<ViajeResponseDTO>>

    @GET("api/viajes/usuario/{usuarioId}/proximo")
    suspend fun obtenerProximoViaje(@Path("usuarioId") usuarioId: Int): Response<ViajeResponseDTO>

    @PUT("api/viajes/{id}")
    suspend fun actualizarViaje(
        @Path("id") viajeId: Int,
        @Body request: ViajeRequestDTO
    ): Response<ViajeResponseDTO>
}