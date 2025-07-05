package com.jah.mapeo.modelo.API.Itinerario

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItinerarioDTO(
    val itinerario_id: Int?,
    val dia: String?,
    val viaje_id: Int?
) : Parcelable

