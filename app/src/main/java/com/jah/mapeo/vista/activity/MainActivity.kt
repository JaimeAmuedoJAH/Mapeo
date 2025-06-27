package com.jah.mapeo.vista.activity

import android.content.Context
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
import androidx.compose.material.icons.automirrored.filled.ExitToApp
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
import androidx.compose.ui.unit.sp
import com.jah.mapeo.R
import com.jah.mapeo.contralador.AppDatabase
import com.jah.mapeo.modelo.LoginRequest
import com.jah.mapeo.modelo.LoginResponse
import com.jah.mapeo.modelo.RetrofitClient
import com.jah.mapeo.modelo.RetrofitClientPublic
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa Retrofit con el contexto de aplicación
        RetrofitClient.init(this)

        // Verifica si hay token guardado (sesión activa)
        val prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE)
        val token = prefs.getString("jwt_token", null)

        if (token != null) {
            // Hay sesión activa, redirige al Inicio
            startActivity(Intent(this, Inicio::class.java))
            finish()
        } else {
            // No hay sesión activa, muestra la pantalla de login
            setContent {
                LoginScreen()
            }
        }
    }


    @Composable
    fun LoginScreen() {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val db = remember { AppDatabase.getDatabase(context) }
        val usuarioDao = db.usuarioDao()

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var mostrarDialogoSalir by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(colorResource(id = R.color.background)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Barra superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.barras_letra))
                    .size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Salir",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(30.dp)
                        .clickable { mostrarDialogoSalir = true },
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

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "¿Has olvidado tu contraseña? Recuperar contraseña",
                fontSize = 12.sp,
                color = colorResource(id = R.color.barras_letra),
                modifier = Modifier.clickable { recuperarContrasena(context) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val loginRequest = LoginRequest(email, password)

                    RetrofitClientPublic.instance.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {if (response.isSuccessful) {
                            val token = response.body()?.token
                            if (token != null) {
                                // Guardamos el token
                                val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                                prefs.edit().putString("jwt_token", token).apply()

                                // Redirigir al inicio
                                context.startActivity(Intent(context, Inicio::class.java))
                                if (context is AppCompatActivity) {
                                    context.finish()
                                }
                            } else {
                                Toast.makeText(context, "Token vacío. Intenta nuevamente.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                        }

                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(context, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                        }

                    })


                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons)),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Iniciar Sesión", color = colorResource(id = R.color.barras_letra))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "¿No tienes cuenta? Regístrate",
                fontSize = 12.sp,
                color = colorResource(id = R.color.barras_letra),
                modifier = Modifier.clickable { registro(context) }
            )

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
                        finish()
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

    private fun registro(context: Context) {
        val intent = Intent(context, Registro::class.java)
        context.startActivity(intent)
    }

    private fun recuperarContrasena(context: Context) {
        val intent = Intent(context, RecuperarContrasena::class.java)
        context.startActivity(intent)
    }
}