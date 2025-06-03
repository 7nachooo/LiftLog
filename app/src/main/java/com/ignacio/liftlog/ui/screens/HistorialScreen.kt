package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ignacio.liftlog.ui.screens.HistorialScreen


@Composable
fun HistorialScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Historial de Sueño 🛌",
            style = MaterialTheme.typography.titleLarge
        )

        // Aquí iría la lógica para mostrar días anteriores o gráficos
        Text("Aún no hay datos históricos.")
    }
}
