package com.jah.mapeo.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viajes")
data class Viaje(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val numDias: Int,
    val fechaInicio: String,
    val fechaFin: String,
    val estancia: String,
    val transporte: String,
    val presupuesto: Double,
    val personas: Int,
    val notas: String,
    val usuarioId: Int,
    val itinerario: List<Itinerario>
) {
    override fun toString(): String {
        return "Viaje(id=$id, titulo='$titulo', numDias=$numDias, fechaInicio='$fechaInicio', fechaFin='$fechaFin', estancia='$estancia', transporte='$transporte', presupuesto=$presupuesto, personas=$personas, notas='$notas', usuarioId=$usuarioId, itinerario=$itinerario)"
    }
}
