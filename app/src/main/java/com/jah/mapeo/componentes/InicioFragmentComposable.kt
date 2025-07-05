package com.jah.mapeo.componentes

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jah.mapeo.R
import com.jah.mapeo.contralador.ViajeRepository
import com.jah.mapeo.modelo.API.Viajes.ViajeResponseDTO

@Composable
fun InicioFragmentComposable() {
    val context = LocalContext.current
    var viaje by remember { mutableStateOf<ViajeResponseDTO?>(null) }
    var cargando by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            viaje = ViajeRepository.obtenerProximoViajeRemoto(context)
        } catch (e: Exception) {
            error = true
        } finally {
            cargando = false
        }
    }

    when {
        cargando -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text("⏳ Cargando próximo viaje...")
        }

        error -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text("⚠️ Error al obtener el viaje")
        }

        viaje != null -> {
            // Recuperamos URI local desde SharedPreferences
            val prefs = context.getSharedPreferences("imagenes_viaje", Context.MODE_PRIVATE)
            val ruta = viaje?.viaje_id?.let { prefs.getString("imagen_uri_$it", null) }
            val uri = ruta?.let { android.net.Uri.parse(it) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background))
                    .verticalScroll(rememberScrollState())
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Mostrar imagen personalizada si existe
                if (uri != null) {
                    androidx.compose.foundation.Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Imagen del viaje",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(180.dp)
                            .background(colorResource(id = R.color.background))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.asturias),
                        contentDescription = "Imagen por defecto",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(180.dp)
                            .background(colorResource(id = R.color.background))
                    )
                }

                // Campos del viaje
                CampoLectura(viaje!!.titulo ?: "-", "Nombre de viaje")
                Spacer(Modifier.height(16.dp))
                CampoLectura(viaje!!.lugar ?: "-", "Lugar")
                Spacer(Modifier.height(16.dp))
                CampoLectura("${viaje!!.num_dias ?: 0}", "Número de días")
                Spacer(Modifier.height(16.dp))
                CampoLectura(viaje!!.estancia ?: "-", "Estancia")
                Spacer(Modifier.height(16.dp))
                CampoLectura("${viaje!!.fecha_ini} a ${viaje!!.fecha_fin}", "Fechas")
                Spacer(Modifier.height(16.dp))
                CampoLectura(viaje!!.transporte ?: "-", "Transporte")
                Spacer(Modifier.height(16.dp))
                CampoLectura("${viaje!!.presupuesto ?: 0}€", "Presupuesto")
                Spacer(Modifier.height(16.dp))
                CampoLectura(viaje!!.notas ?: "-", "Notas")
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Navegación a la pantalla de itinerario
                        // Reemplaza "itinerario/${viaje!!.id}" con tu ruta real
                    },
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
                ) {
                    Text("Ver itinerario")
                }
            }
        }

        else -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text("🎒 No tienes viajes próximos")
        }
    }
}