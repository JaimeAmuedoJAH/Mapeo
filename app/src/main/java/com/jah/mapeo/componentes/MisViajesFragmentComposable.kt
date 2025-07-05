package com.jah.mapeo.componentes

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jah.mapeo.R
import com.jah.mapeo.contralador.ViajeRepository
import com.jah.mapeo.modelo.POJO.ViajeConImagen
import com.jah.mapeo.vista.activity.VerViaje
import java.io.File
import java.io.FileOutputStream

@Composable
fun MisViajesFragmentComposable() {
    val context = LocalContext.current
    var viajes by remember { mutableStateOf<List<ViajeConImagen>>(emptyList()) }

    LaunchedEffect(Unit) {
        val remotos = ViajeRepository.obtenerViajesRemotos(context)

        val viajesConImagen = remotos.map { viaje ->
            val prefs = context.getSharedPreferences("imagenes_viaje", Context.MODE_PRIVATE)
            val ruta = prefs.getString("imagen_uri_${viaje.viaje_id}", null)
            ViajeConImagen(viaje, ruta)
        }

        viajes = viajesConImagen
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (viajes.isEmpty()) {
            Text("Crea tu primer viaje en la sección de Planificador 🌍", style = MaterialTheme.typography.bodyLarge)
        } else {
            viajes.forEachIndexed { index, viajeConImagen ->
                TarjetaViajeEditable(
                    viaje = viajeConImagen,
                    onImagenSeleccionada = { nuevaUri ->
                        val rutaLocal = guardarImagenDesdeUri(context, nuevaUri, "imagen_viaje_${viajeConImagen.viaje.viaje_id}")
                        rutaLocal?.let {
                            val prefs = context.getSharedPreferences("imagenes_viaje", Context.MODE_PRIVATE)
                            prefs.edit().putString("imagen_uri_${viajeConImagen.viaje.viaje_id}", it).apply()

                            viajes = viajes.toMutableList().also {
                                it[index] = viajeConImagen.copy(imagenUri = it.toString())
                            }
                        }
                    },
                    onCardClick = {
                        val intent = Intent(context, VerViaje::class.java).apply {
                            putExtra("viaje", viajeConImagen.viaje)
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun TarjetaViajeEditable(
    viaje: ViajeConImagen,
    onImagenSeleccionada: (Uri) -> Unit,
    onCardClick: () -> Unit
) {
    val context = LocalContext.current
    var imagenUri by remember { mutableStateOf<Uri?>(viaje.imagenUri?.let { Uri.parse(it) }) }

    val bitmap = remember(imagenUri) {
        imagenUri?.let {
            val archivo = File(it.path ?: "")
            if (archivo.exists()) BitmapFactory.decodeFile(archivo.absolutePath) else null
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imagenUri = it
                onImagenSeleccionada(it)
            }
        }
    )

    val datos = viaje.viaje

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clickable { launcher.launch("image/*") }
                )
            } else {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(colorResource(id = R.color.background))
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Seleccionar imagen", color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(datos.titulo ?: "Sin título", style = MaterialTheme.typography.titleMedium)
            Text("${datos.fecha_ini} - ${datos.fecha_fin}", style = MaterialTheme.typography.labelSmall)
        }
    }
}

// 🔒 Con protección contra bitmap nulo
fun guardarImagen(context: Context, bitmap: Bitmap?, nombre: String): String? {
    if (bitmap == null) return null
    val archivo = File(context.filesDir, "$nombre.png")
    FileOutputStream(archivo).use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
    }
    return archivo.absolutePath
}

// 🔒 Con verificación de URI válido
fun guardarImagenDesdeUri(context: Context, uri: Uri, nombre: String): String? {
    val inputStream = context.contentResolver.openInputStream(uri) ?: return null
    val bitmap = BitmapFactory.decodeStream(inputStream) ?: return null
    return guardarImagen(context, bitmap, nombre)
}
