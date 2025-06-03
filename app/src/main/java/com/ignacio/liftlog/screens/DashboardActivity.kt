package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.ignacio.liftlog.ui.components.BottomNavigationBar
import com.ignacio.liftlog.ui.screens.*
import com.ignacio.liftlog.utils.EntrenamientoSeleccionadoViewModel
import com.ignacio.liftlog.utils.EntrenamientosViewModel
import com.jakewharton.threetenabp.AndroidThreeTen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val entrenamientosViewModel = EntrenamientosViewModel(this)
                DashboardNavHost(navController, entrenamientosViewModel)
            }
        }
    }
}

@Composable
fun DashboardNavHost(navController: NavHostController, entrenamientosViewModel: EntrenamientosViewModel) {
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { paddingValues ->
        NavHost(navController, startDestination = BottomTabScreen.Home.route, Modifier.padding(paddingValues)) {
            composable(BottomTabScreen.Home.route) { HomeTabScreen(navController) }
            composable(BottomTabScreen.Progress.route) {
                ProgresoTabScreen(navController, entrenamientosViewModel)
            }
            composable(BottomTabScreen.Community.route) { ComunidadTabScreen() }
            composable(BottomTabScreen.Profile.route) { PerfilTabScreen(navController) }


            composable("settings") {
                SettingsScreen(navController = navController)
            }


            composable("editar_perfil") {
                EditProfileScreen(
                    initialName = "Nacho Arcala",
                    initialEmail = "nacho3@liftlog.com",
                    initialWeight = "72",
                    onSave = { name, email, weight ->
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
