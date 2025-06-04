package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.ignacio.liftlog.ui.components.BottomNavigationBar
import com.ignacio.liftlog.ui.screens.*
import com.ignacio.liftlog.utils.EntrenamientoSeleccionadoViewModel
import com.ignacio.liftlog.utils.EntrenamientosViewModel
import com.ignacio.liftlog.models.PerfilViewModel
import com.jakewharton.threetenabp.AndroidThreeTen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val entrenamientosViewModel = EntrenamientosViewModel(this)
                val perfilViewModel: PerfilViewModel = viewModel()
                DashboardNavHost(navController, entrenamientosViewModel, perfilViewModel)
            }
        }
    }
}

@Composable
fun DashboardNavHost(
    navController: NavHostController,
    entrenamientosViewModel: EntrenamientosViewModel,
    perfilViewModel: PerfilViewModel
) {
    val usuario = perfilViewModel.usuario.collectAsState().value

    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { paddingValues ->
        NavHost(navController, startDestination = BottomTabScreen.Home.route, Modifier.padding(paddingValues)) {
            composable(BottomTabScreen.Home.route) {
                HomeTabScreen(navController)
            }
            composable(BottomTabScreen.Progress.route) {
                ProgresoTabScreen(navController, entrenamientosViewModel)
            }
            composable(BottomTabScreen.Community.route) {
                ComunidadTabScreen()
            }
            composable(BottomTabScreen.Profile.route) {
                PerfilTabScreen(navController)
            }

            composable("settings") {
                SettingsScreen(navController = navController)
            }

            composable("editar_perfil") {
                EditProfileScreen(
                    initialName = usuario.nombre,
                    initialEmail = usuario.email,
                    initialWeight = usuario.peso.toString(),
                    initialHeight = usuario.altura.toString(),
                    onSave = { name, email, weight, height ->
                        perfilViewModel.actualizarUsuario(
                            nombre = name,
                            email = email,
                            peso = weight.toIntOrNull() ?: 0,
                            altura = height.toIntOrNull() ?: 0
                        )
                        navController.popBackStack()
                    },
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }

            composable("registro_entrenamiento") {
                RegistroEntrenamientoScreen(entrenamientosViewModel)
            }

            composable("nutricion") {
                NuevaNutricionTabScreen()
            }
        }
    }
}
