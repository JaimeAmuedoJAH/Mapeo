package com.jah.mapeo.util

import android.app.DatePickerDialog
import android.content.Context
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

object DatePickerUtils {
    fun mostrarSelectorFecha(context: Context, onFechaSeleccionada: (String) -> Unit) {
        val hoy = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val fecha = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year)
                onFechaSeleccionada(fecha)
            },
            hoy.get(Calendar.YEAR),
            hoy.get(Calendar.MONTH),
            hoy.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun formatearFechaParaApi(input: String, formato: String = "dd-MM-yyyy"): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern(formato)
            val fecha = LocalDate.parse(input, formatter)
            fecha.format(formatter) // mantiene el mismo formato
        } catch (e: Exception) {
            ""
        }
    }
}
