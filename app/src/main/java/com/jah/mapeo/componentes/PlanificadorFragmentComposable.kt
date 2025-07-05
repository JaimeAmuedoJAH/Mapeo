package com.jah.mapeo.componentes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jah.mapeo.R
import com.jah.mapeo.contralador.ViajeRepository
import com.jah.mapeo.contralador.ViajeRepository.calcularDiasYGenerarEtiquetas
import com.jah.mapeo.contralador.ViajeService
import com.jah.mapeo.modelo.API.Itinerario.ItinerarioDTO
import com.jah.mapeo.modelo.API.RetrofitProvider
import com.jah.mapeo.modelo.API.Viajes.ViajeRequestDTO
import com.jah.mapeo.util.DatePickerUtils
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Composable para mostrar el fragmento del planificador.
 */
@Composable
fun PlanificadorFragmentComposable() {
    var formularioVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { formularioVisible = !formularioVisible },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
        ) {
            Text(if (formularioVisible) "Ocultar formulario" else "Planifica tu siguiente viaje")
        }

        AnimatedVisibility(
            visible = formularioVisible,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column {
                Spacer(Modifier.height(16.dp))
                FormularioPlanificador()
            }
        }
    }
}

/**
 * Muestra el formulario del planificador del viaje.
 */
@Composable
fun FormularioPlanificador() {
    var itinerarioVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var titulo by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var numDias by remember { mutableStateOf("") }
    var estancia by remember { mutableStateOf("") }
    var transporte by remember { mutableStateOf("") }
    var presupuesto by remember { mutableStateOf("") }
    var lugar by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    val servicio = RetrofitProvider.viajeApi


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.background))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CampoTexto(titulo, { titulo = it }, "Nombre del viaje")
        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(lugar, { lugar = it }, "Próximo destino")
        Spacer(modifier = Modifier.height(16.dp))

        CampoFecha(
            valor = fechaInicio,
            etiqueta = "Fecha de inicio",
            onFechaSeleccionada = { seleccion ->
                fechaInicio = seleccion
                if (fechaFin.isNotEmpty()) {
                    numDias = calcularDias(seleccion, fechaFin).toString()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoFecha(
            valor = fechaFin,
            etiqueta = "Fecha de fin",
            onFechaSeleccionada = { seleccion ->
                fechaFin = seleccion
                if (fechaInicio.isNotEmpty()) {
                    numDias = calcularDias(seleccion, fechaInicio).toString()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(numDias, { numDias = it }, "Num días (Autocalculado)", readOnly = true)
        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(estancia, { estancia = it }, "Estancia")
        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(transporte, { transporte = it }, "Transporte")
        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(presupuesto, { presupuesto = it }, "Presupuesto")
        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(notas, { notas = it }, "Notas importantes")
        Spacer(modifier = Modifier.height(16.dp))

        val scope = rememberCoroutineScope()

        Button(
            onClick = {
                scope.launch {
                    try {
                        val fechaInicioFormateada = DatePickerUtils.formatearFechaParaApi(fechaInicio)
                        val fechaFinFormateada = DatePickerUtils.formatearFechaParaApi(fechaFin)


                        // 1. Generar etiquetas por día
                        val etiquetasDias = calcularDiasYGenerarEtiquetas(fechaInicio, fechaFin)


                        // 2. Crear lista de itinerarios vacíos con etiquetas de días
                        val itinerarios = etiquetasDias.map { diaLabel ->
                            ItinerarioDTO(
                                itinerario_id = null,
                                dia = diaLabel,
                                viaje_id = null
                            )
                        }

                        // 3. Crear objeto completo del viaje
                        val viajeDTO = ViajeRequestDTO(
                            lugar = lugar,
                            titulo = titulo,
                            num_dias = etiquetasDias.size,
                            fecha_ini = fechaInicioFormateada,
                            fecha_fin = fechaFinFormateada,
                            estancia = estancia,
                            transporte = transporte,
                            presupuesto = presupuesto.toIntOrNull(),
                            notas = notas,
                            usuario_id = ViajeRepository.obtenerUsuarioIdLocal(context),
                            itinerario = itinerarios
                        )

                        val servicio = RetrofitProvider.create(ViajeService::class.java)
                        val response = servicio.crearViaje(viajeDTO)


                        if (response.isSuccessful) {
                            val nuevoViaje = response.body()
                            val viajeId = nuevoViaje?.viaje_id

                            if (viajeId != null) {
                                // 🔁 Cargar imagen por defecto desde drawable
                                val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.asturias)

                                // 💾 Guardarla en almacenamiento interno
                                val rutaLocal = guardarImagen(context, bitmap, "imagen_viaje_$viajeId")

                                // 🧠 Guardar la ruta en SharedPreferences
                                val prefs = context.getSharedPreferences("imagenes_viaje", Context.MODE_PRIVATE)
                                prefs.edit().putString("imagen_uri_$viajeId", rutaLocal).apply()
                            }

                            Toast.makeText(context, "✅ Viaje creado correctamente", Toast.LENGTH_SHORT).show()
                            itinerarioVisible = true
                        }
                        else {
                            Toast.makeText(context, "❌ Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                        }

                    } catch (e: Exception) {
                        Toast.makeText(context, "🚫 Error de red: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
        ) {
            Text("Guardar viaje")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = itinerarioVisible,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /* Acción */ },
                    modifier = Modifier
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
                ) {
                    Text("Realiza tu itinerario")
                }
            }
        }
    }
}

fun calcularDias(inicioStr: String, finStr: String): Int {
    return try {
        val formato = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val inicio = LocalDate.parse(inicioStr, formato)
        val fin = LocalDate.parse(finStr, formato)
        ChronoUnit.DAYS.between(fin, inicio).toInt() + 1
    } catch (e: Exception) {
        0
    }
}

fun guardarImagen(context: Context, bitmap: Bitmap, nombre: String): String {
    val archivo = File(context.filesDir, "$nombre.png")
    FileOutputStream(archivo).use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
    }
    return archivo.absolutePath
}