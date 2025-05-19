package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EstadisticasTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Estadísticas generales 📊",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Consulta tus métricas clave como evolución de peso, PRs, consistencia mensual y progreso físico.",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )


    }
}
