package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ignacio.liftlog.ui.components.BottomNavigationBar
import com.ignacio.liftlog.ui.screens.*

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                DashboardNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun DashboardNavHost(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomTabScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomTabScreen.Home.route) {
                HomeTabScreen()
            }
            composable(BottomTabScreen.Progress.route) {
                ProgresoTabScreen()
            }
            composable(BottomTabScreen.Community.route) {
                com.ignacio.liftlog.ui.screens.ComunidadTabScreen()
            }
            composable(BottomTabScreen.Profile.route) {
                PerfilTabScreen()
            }
        }
    }
}








