package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ignacio.liftlog.ui.screens.SettingsScreen
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController() // Crear NavController
                SettingsScreen(navController) // Pasarlo
            }
        }
    }
}