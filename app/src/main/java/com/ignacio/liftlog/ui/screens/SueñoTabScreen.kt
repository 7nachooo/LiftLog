package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SueñoTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Resumen de sueño 😴",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Aquí verás tus horas dormidas, consistencia del sueño, y consejos personalizados para mejorar tu descanso.",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )


    }
}

