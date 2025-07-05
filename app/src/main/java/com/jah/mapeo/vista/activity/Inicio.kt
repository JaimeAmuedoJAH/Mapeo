package com.jah.mapeo.vista.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jah.mapeo.R
import com.jah.mapeo.componentes.InicioFragmentComposable
import com.jah.mapeo.componentes.MisViajesFragmentComposable
import com.jah.mapeo.componentes.PerfilFragmentComposable
import com.jah.mapeo.componentes.PlanificadorFragmentComposable
import com.jah.mapeo.contralador.estaExpirado
import com.jah.mapeo.contralador.obtenerExpiracionDeToken

/**
 * Clase principal de la aplicación.
 * Contiene la navegación entre las diferentes pantallas (Inicio, Planificador, Viajes, Perfil).
 * @author Jaime Amuedo
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
}