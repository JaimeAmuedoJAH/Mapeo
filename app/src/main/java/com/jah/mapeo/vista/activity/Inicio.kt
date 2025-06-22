package com.jah.mapeo.vista.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.res.colorResource
import com.jah.mapeo.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }

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

    @Composable
    fun InicioFragmentComposable() {

    }

    @Composable
    fun PlanificadorFragmentComposable() {

    }

    @Composable
    fun MisViajesFragmentComposable() {

    }

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PerfilFragmentComposable() {
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
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Salir",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(30.dp)
                        .clickable {
                            settingComposable()
                        },
                    tint = Color.White,
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
    }

    private fun BoxScope.settingComposable() {
        TODO("Not yet implemented")
    }
}
