package com.ignacio.liftlog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalendarioScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calendario de entrenamientos",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Aquí podrás visualizar un calendario con tus días entrenados, sesiones futuras y eventos fitness.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
