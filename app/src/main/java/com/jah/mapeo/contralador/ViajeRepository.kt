package com.jah.mapeo.contralador

import android.content.Context
import android.util.Log
import com.jah.mapeo.modelo.API.RetrofitProvider
import com.jah.mapeo.modelo.API.Viajes.ViajeResponseDTO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object ViajeRepository {
    suspend fun obtenerViajesRemotos(context: Context): List<ViajeResponseDTO> {
        val usuarioId = obtenerUsuarioIdLocal(context)
        val servicio = RetrofitProvider.create(ViajeService::class.java)

        val response = servicio.obtenerViajesPorUsuario(usuarioId)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    fun calcularDiasYGenerarEtiquetas(inicio: String, fin: String): List<String> {
        val formato = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val fechaInicio = LocalDate.parse(inicio, formato)
        val fechaFin = LocalDate.parse(fin, formato)

        val cantidadDias = ChronoUnit.DAYS.between(fechaInicio, fechaFin).toInt() + 1

        return (1..cantidadDias).map { dia -> "Día $dia" }
    }

    fun obtenerUsuarioIdLocal(context: Context): Int {
        val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        val id = prefs.getInt("usuario_id", -1)
        return id

    }

    suspend fun obtenerProximoViajeRemoto(context: Context): ViajeResponseDTO? {
        val usuarioId = obtenerUsuarioIdLocal(context)
        val servicio = RetrofitProvider.create(ViajeService::class.java)
        val response = servicio.obtenerProximoViaje(usuarioId)

        return if (response.isSuccessful) response.body() else null
    }
}
