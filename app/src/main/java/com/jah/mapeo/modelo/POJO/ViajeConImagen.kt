package com.jah.mapeo.modelo.POJO

import com.jah.mapeo.modelo.API.Viajes.ViajeResponseDTO
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ViajeConImagen(
    val viaje: ViajeResponseDTO,
    val imagenUri: String?
) : Parcelable

