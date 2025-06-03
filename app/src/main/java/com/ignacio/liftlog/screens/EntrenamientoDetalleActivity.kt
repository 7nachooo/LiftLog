package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.ignacio.liftlog.models.Entrenamiento
import com.ignacio.liftlog.models.LugarEntreno
import com.ignacio.liftlog.ui.screens.EntrenamientoDetalleScreen

class EntrenamientoDetalleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entrenamiento = Entrenamiento(
            fecha = "2025-05-27",
            tipo = "Cardio HIIT",
            duracion = 45,
            calorias = 420,
            lugar = LugarEntreno.GIMNASIO
        )

        setContent {
            MaterialTheme {
                EntrenamientoDetalleScreen(entreno = entrenamiento)
            }
        }
    }
}
