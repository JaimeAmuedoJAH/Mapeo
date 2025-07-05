package com.jah.mapeo.vista.activity

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jah.mapeo.R
import com.jah.mapeo.componentes.CampoFecha
import com.jah.mapeo.modelo.API.Viajes.ViajeResponseDTO
import java.io.File

class VerViaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viajeRecibido = intent.getParcelableExtra<ViajeResponseDTO>("viaje")

        setContent {
            VerViajeComposable(viaje = viajeRecibido)
        }

    }

    @Composable
    fun VerViajeComposable(viaje: ViajeResponseDTO?) {
        val context = LocalContext.current

        val ruta = remember(viaje?.viaje_id) {
            val prefs = context.getSharedPreferences("imagenes_viaje", Context.MODE_PRIVATE)
            prefs.getString("imagen_uri_${viaje?.viaje_id}", null) // ✅ devuelves el resultado directamente
        }



        val bitmap = remember(ruta) {
            ruta?.let {
                val archivo = File(it)
                if (archivo.exists()) BitmapFactory.decodeFile(archivo.absolutePath) else null
            }
        }

        val textFieldColors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.barras_letra),
            unfocusedBorderColor = colorResource(id = R.color.barras_letra),
            cursorColor = colorResource(id = R.color.barras_letra),
            focusedTextColor = colorResource(id = R.color.barras_letra)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .background(colorResource(id = R.color.background))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Imagen del viaje",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(8.dp)
                )
            } else {
                Text("⚠️ No se pudo cargar la imagen", color = Color.Red)
            }


            viaje?.let {
                OutlinedTextField(
                    value = it.titulo.orEmpty(),
                    onValueChange = {},
                    label = { Text("Nombre del viaje") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = it.lugar.orEmpty(),
                    onValueChange = {},
                    label = { Text("Próximo destino") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                var fechaInicio by remember { mutableStateOf(it.fecha_ini) }
                CampoFecha(
                    valor = fechaInicio,
                    etiqueta = "Fecha de inicio",
                    onFechaSeleccionada = { nuevaFecha -> fechaInicio = nuevaFecha }
                )

                Spacer(modifier = Modifier.height(16.dp))

                var fechaFin by remember { mutableStateOf(it.fecha_fin) }
                CampoFecha(
                    valor = fechaFin,
                    etiqueta = "Fecha final",
                    onFechaSeleccionada = { nuevaFecha -> fechaFin = nuevaFecha }
                )


                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = it.num_dias?.toString().orEmpty(),
                    onValueChange = {},
                    label = { Text("Num días (Autocalculado)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = it.estancia.orEmpty(),
                    onValueChange = {},
                    label = { Text("Estancia") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = it.transporte.orEmpty(),
                    onValueChange = {},
                    label = { Text("Transporte") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = it.presupuesto?.toString().orEmpty(),
                    onValueChange = {},
                    label = { Text("Presupuesto") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = it.notas.orEmpty(),
                    onValueChange = {},
                    label = { Text("Notas") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Ver itinerario */ },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
                ) {
                    Text("Ver itinerario")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Guardar cambios */ },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
                ) {
                    Text("Guardar cambios")
                }
            } ?: run {
                Text("❌ No se pudo cargar el viaje", color = Color.Red)
            }
        }
    }
}