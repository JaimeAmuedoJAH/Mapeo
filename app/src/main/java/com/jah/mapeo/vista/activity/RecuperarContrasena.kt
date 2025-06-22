package com.jah.mapeo.vista.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jah.mapeo.R

class RecuperarContrasena : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecuperarContrasenaScreen();
        }
    }

    @Composable
    fun RecuperarContrasenaScreen() {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(colorResource(id = R.color.background)) // Fondo claro como en la imagen
                .systemBarsPadding(),
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            // Barra superior con fondo oscuro y botón atrás
            Box(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(colorResource(id = R.color.barras_letra)), // color oscuro barra (puedes usar colorResource)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterEnd)
                        .padding(end = 12.dp)
                        .size(32.dp)
                        .clickable {finish()},
                    tint = Color.Companion.White
                )
            }

            Spacer(modifier = Modifier.Companion.height(40.dp))

            // Logo central
            Image(
                painter = painterResource(id = R.drawable.logo_ruteo),
                contentDescription = "Logo",
                modifier = Modifier.Companion.size(140.dp)
            )

            Spacer(modifier = Modifier.Companion.height(40.dp))

            // Campo email
            var email by remember { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.Companion.fillMaxWidth(0.8f),
                singleLine = true
            )

            Spacer(modifier = Modifier.Companion.height(24.dp))

            // Botón enviar
            Button(
                onClick = { /* TODO enviar correo */ },
                modifier = Modifier.Companion
                    .fillMaxWidth(0.8f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.buttons), // color verde azulado parecido)) // color verde azulado parecido
                )
            ) {
                Text("Enviar correo", color = colorResource(id = R.color.barras_letra))
            }
        }
    }

}