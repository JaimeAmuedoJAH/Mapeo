package com.jah.mapeo.modelo.API.Viajes

import com.jah.mapeo.modelo.API.Itinerario.ItinerarioDTO
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ViajeResponseDTO(
    val viaje_id: Int,
    val lugar: String?,
    val titulo: String?,
    val num_dias: Int?,
    val fecha_ini: String,
    val fecha_fin: String,
    val estancia: String?,
    val transporte: String?,
    val presupuesto: Int?,
    val personas: List<String>?,
    val notas: String?,
    val usuario_id: Int,
    val itinerario: List<ItinerarioDTO>?
) : Parcelable

