package com.jah.mapeo.vista.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jah.mapeo.R

/**
 * Clase principal de la aplicación.
 * Contiene la navegación entre las diferentes pantallas (Inicio, Planificador, Viajes, Perfil).
 * @author Jaime Amuedo
 * @version 1.0.0
 */
class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }

    /**
     * Composable para mostrar la barra de navegación inferior de las pantallas.
     * Cada uno de los iconos lleva a uno de los apartados principales de la aplicación.
     */
    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = colorResource(id = R.color.barras_letra)
                ) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Inicio"
                            )
                        },
                        label = {
                            Text("Inicio")
                        },
                        selected = currentRoute == "inicio",
                        onClick = {
                            navController.navigate("inicio") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.white),
                            unselectedIconColor = colorResource(id = R.color.white),
                            selectedTextColor = colorResource(id = R.color.white),
                            unselectedTextColor = colorResource(id = R.color.white),
                            indicatorColor = colorResource(id = R.color.buttons)
                        )
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                Icons.Filled.DateRange,
                                contentDescription = "Planificador"
                            )
                        },
                        label = { Text("Planificador") },
                        selected = currentRoute == "planificador",
                        onClick = {
                            navController.navigate("planificador") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.white),
                            unselectedIconColor = colorResource(id = R.color.white),
                            selectedTextColor = colorResource(id = R.color.white),
                            unselectedTextColor = colorResource(id = R.color.white),
                            indicatorColor = colorResource(id = R.color.buttons)
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.List, contentDescription = "Viajes") },
                        label = { Text("Viajes") },
                        selected = currentRoute == "viajes",
                        onClick = {
                            navController.navigate("viajes") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.white),
                            unselectedIconColor = colorResource(id = R.color.white),
                            selectedTextColor = colorResource(id = R.color.white),
                            unselectedTextColor = colorResource(id = R.color.white),
                            indicatorColor = colorResource(id = R.color.buttons)
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = currentRoute == "perfil",
                        onClick = {
                            navController.navigate("perfil") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.white),
                            unselectedIconColor = colorResource(id = R.color.white),
                            selectedTextColor = colorResource(id = R.color.white),
                            unselectedTextColor = colorResource(id = R.color.white),
                            indicatorColor = colorResource(id = R.color.buttons)
                        )
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "inicio",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("inicio") { InicioFragmentComposable() }
                composable("planificador") { PlanificadorFragmentComposable() }
                composable("viajes") { MisViajesFragmentComposable() }
                composable("perfil") { PerfilFragmentComposable() }
            }
        }
    }

    /**
     * Composable para mostrar el fragmento de inicio.
     */
    @Composable
    fun InicioFragmentComposable() {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(all = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Image(
                painter = painterResource(id = R.drawable.asturias),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
                    .border(width = 1.dp, color = colorResource(id = R.color.buttons))
                    .clickable{}

            )

            Spacer(modifier = Modifier.height(8.dp))

            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.barras_letra),
                unfocusedBorderColor = colorResource(id = R.color.barras_letra),
                cursorColor = colorResource(id = R.color.barras_letra),
                focusedTextColor = colorResource(id = R.color.barras_letra),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background))
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){

                OutlinedTextField(
                    value = obtenerNombreViaje(),
                    onValueChange = {},
                    label = {Text("Nombre de viaje")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerNumDias(),
                    onValueChange = {  },
                    label = { Text("Número de días") },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerEstancia(),
                    onValueChange = {},
                    label = {Text("Estancia")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerFechaViaje(),
                    onValueChange = {},
                    label = {Text("Fecha del viaje ")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerTransporte(),
                    onValueChange = {},
                    label = {Text("Transporte")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerPresupuesto(),
                    onValueChange = {},
                    label = {Text("Presupuesto")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerPersonas(),
                    onValueChange = {},
                    label = {Text("Personas")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = obtenerNotas(),
                    onValueChange = {},
                    label = {Text("Notas")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    enabled = false,
                    readOnly = true,
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }

    /**
     * Metodos para obtener los datos del fragment inicio
     */
    fun obtenerTransporte() : String  {

        return "Coche"
    }

    fun obtenerPresupuesto() : String  {

        return "1000" + "€"
    }

    fun obtenerPersonas() : String  {

        return "Jaime, Nani, Jou, Javi"
    }

    fun obtenerNotas() : String  {

        return "Llevar calzado deportivo "
    }

    fun obtenerEstancia() : String {

        return "Sierra de Pola"
    }

    fun obtenerFechaViaje() : String {

        return "10 de julio a 17 de julio"
    }

    fun obtenerNumDias() : String{

       return "8"
    }

    fun obtenerNombreViaje() : String {

        return "Asturias"
    }

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
                .padding(all = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { formularioVisible = !formularioVisible },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
            ) {
                Text(text = if (formularioVisible) "Ocultar formulario" else "Mostrar formulario")
            }
            //Animcación de despliegue hacia abajo del formulario.
            AnimatedVisibility(
                visible = formularioVisible,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.background))
                .padding(16.dp)
        ) {
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.barras_letra),
                unfocusedBorderColor = colorResource(id = R.color.barras_letra),
                cursorColor = colorResource(id = R.color.barras_letra),
                focusedTextColor = colorResource(id = R.color.barras_letra)
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* manejar input */ },
                label = { Text("Título del viaje") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = { /* manejar input */ },
                label = { Text("Destino") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttons))
            ) {
                Text("Guardar viaje")
            }
        }
    }

    /**
     * Composable para mostrar el fragmento de mis viajes creados.
     */
    @Composable
    fun MisViajesFragmentComposable() {

    }

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.barras_letra))
                    .size(60.dp)
            ) {
                Text(
                    text = "Perfil",
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(start = 16.dp),
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Salir",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(30.dp)
                        .clickable {
                            mostrarDialogoSalir = true
                        },
                    tint = Color.White
                )
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Salir",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 60.dp)
                        .size(30.dp)
                        .clickable {},
                    tint = Color.White
                )
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Salir",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 105.dp)
                        .size(30.dp)
                        .clickable {
                           // SettingComposable()
                        },
                    tint = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.barras_letra),
                unfocusedBorderColor = colorResource(id = R.color.barras_letra),
                cursorColor = colorResource(id = R.color.barras_letra),
                focusedTextColor = colorResource(id = R.color.barras_letra)
            )

            OutlinedTextField(
                value = obtenerNombre(),
                onValueChange = {},
                label = {Text("Nombre de usuario")},
                modifier = Modifier.fillMaxWidth(0.8f),
                enabled = false,
                readOnly = true,
                colors = textFieldColors
            )

            OutlinedTextField(
                value = obtenerMail(),
                onValueChange = {  },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.8f),
                enabled = false,
                readOnly = true,
                colors = textFieldColors
            )
            OutlinedTextField(
                value = obtenerProximoViaje(),
                onValueChange = {},
                label = {Text("Próximo viaje")},
                modifier = Modifier.fillMaxWidth(0.8f),
                enabled = false,
                readOnly = true,
                colors = textFieldColors
            )

            OutlinedTextField(
                value = obtenerDeseados(),
                onValueChange = {},
                label = {Text("Viajes deseados  ")},
                modifier = Modifier.fillMaxWidth(0.8f),
                enabled = false,
                readOnly = true,
                colors = textFieldColors
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
                        val prefs = context.getSharedPreferences("auth_prefs", MODE_PRIVATE)
                        prefs.edit().remove("jwt_token").apply()

                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        if (context is AppCompatActivity) {
                            context.finish()
                        }
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
}
