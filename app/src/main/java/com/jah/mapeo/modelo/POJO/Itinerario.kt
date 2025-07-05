package com.jah.mapeo.modelo.POJO

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "itinerario")
data class Itinerario(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dia: LocalDate,
    val descripcion: String,
    val viajeId: Long
) {
    override fun toString(): String {
        return "Itinerario(id=$id, dia=$dia, descripcion='$descripcion', viajeId=$viajeId)"
    }
}