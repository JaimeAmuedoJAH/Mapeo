package com.jah.mapeo.modelo.API.Viajes

import com.google.gson.annotations.SerializedName
import com.jah.mapeo.modelo.API.Itinerario.ItinerarioDTO

data class ViajeRequestDTO(
    @SerializedName("titulo") val titulo: String?,
    @SerializedName("lugar") val lugar: String?,
    @SerializedName("fecha_ini") val fecha_ini: String?,
    @SerializedName("fecha_fin") val fecha_fin: String?,
    @SerializedName("num_dias") val num_dias: Int?,
    @SerializedName("estancia") val estancia: String?,
    @SerializedName("transporte") val transporte: String?,
    @SerializedName("presupuesto") val presupuesto: Int?,
    @SerializedName("notas") val notas: String?,
    @SerializedName("usuario_id") val usuario_id: Int?,
    @SerializedName("itinerario") val itinerario: List<ItinerarioDTO>?
)

