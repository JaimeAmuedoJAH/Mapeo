package com.jah.mapeo.vista.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.jah.mapeo.R
import com.jah.mapeo.modelo.RegistroRequest
import com.jah.mapeo.modelo.RegistroResponse
import com.jah.mapeo.modelo.RetrofitClient
import com.jah.mapeo.modelo.RetrofitClientPublic
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroScreen(onSuccess = { finish() })
        }
    }

    @Composable
    fun RegistroScreen(onSuccess: () -> Unit) {
        val context = LocalContext.current
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(colorResource(id = R.color.background)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.barras_letra))
                    .size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Salir",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(30.dp)
                        .clickable { finish() },
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_ruteo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.barras_letra),
                unfocusedBorderColor = colorResource(id = R.color.barras_letra),
                cursorColor = colorResource(id = R.color.barras_letra),
                focusedTextColor = colorResource(id = R.color.barras_letra)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Verifica tu contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val request = RegistroRequest(
                        nombre = username,
                        email = email,
                        contrasena = password
                    )

                    RetrofitClientPublic.instance.registrar(request).enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {

                                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()

                                // Redirige a login
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                                if (context is AppCompatActivity) context.finish()
                            } else {
                                Toast.makeText(context, "Error del servidor: ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(context, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })


                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons)),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Regístrate", color = colorResource(id = R.color.barras_letra))
            }
        }
    }
}