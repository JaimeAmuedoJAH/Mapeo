package com.jah.mapeo.componentes

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jah.mapeo.R
import com.jah.mapeo.vista.activity.MainActivity

/**
 * Composable para mostrar el fragmento del perfil.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilFragmentComposable() {
    val context = LocalContext.current
    var mostrarDialogoSalir by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.barras_letra))
                .padding(horizontal = 16.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Perfil",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Ajustes",
                tint = Color.White,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(28.dp)
                    .clickable { /* abrir ajustes */ }
            )
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Editar perfil",
                tint = Color.White,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(28.dp)
                    .clickable { /* acción editar */ }
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "Cerrar sesión",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(28.dp)
                    .clickable { mostrarDialogoSalir = true }
            )
        }

        Spacer(Modifier.height(16.dp))

        CampoPerfil(obtenerNombre(), "Nombre de usuario")
        CampoPerfil(obtenerMail(), "Email")
        CampoPerfil(obtenerProximoViaje(), "Próximo viaje")
        CampoPerfil(obtenerDeseados(), "Viajes deseados")

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Versión: 1.0.0",
            fontSize = 10.sp,
            color = colorResource(id = R.color.barras_letra),
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp, bottom = 16.dp)
        )
    }

    if (mostrarDialogoSalir) {
        AlertDialog(
            containerColor = colorResource(id = R.color.background),
            onDismissRequest = { mostrarDialogoSalir = false },
            title = {
                Text("Salir de la app", color = colorResource(id = R.color.barras_letra))
            },
            text = {
                Text("¿Estás seguro de que deseas salir?", color = colorResource(id = R.color.barras_letra))
            },
            confirmButton = {
                TextButton(onClick = {
                    mostrarDialogoSalir = false
                    val prefs = context.getSharedPreferences("auth_prefs", MODE_PRIVATE)
                    prefs.edit().remove("jwt_token").apply()

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    if (context is AppCompatActivity) context.finish()
                }) {
                    Text("Sí", color = colorResource(id = R.color.barras_letra))
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoSalir = false }) {
                    Text("Cancelar", color = colorResource(id = R.color.barras_letra))
                }
            }
        )
    }
}

/**
 * Metodos para obtener los datos del fragment perfil
 */
fun obtenerNombre(): String {

    return "Jaime Amuedo"
}

fun obtenerMail(): String {

    return "jaimeamuedo@gmail.com"
}
fun obtenerProximoViaje(): String {

    return "Asturias"
}

fun obtenerDeseados(): String {

    return "Japón"
}

/**
 * Composable para mostrar el fragmento de ajustes.
 */
/* @Composable
 fun SettingComposable() {
     TODO("Not yet implemented")
 }*/