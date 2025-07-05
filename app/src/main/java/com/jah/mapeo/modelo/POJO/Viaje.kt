package com.jah.mapeo.modelo.POJO

import java.io.Serializable

data class Viaje(
    val id: Int = 0,
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
    val itinerario: List<Itinerario>,
    val lugar: String,
    var imagenUri: String = ""
) : Serializable
 {

    // Campo solo para uso local (imagen almacenada en almacenamiento interno)
    //var imagenUri: String = ""

    override fun toString(): String {
        return "Viaje(id=$id, titulo='$titulo', imagenUri='$imagenUri' ...)"
    }
}