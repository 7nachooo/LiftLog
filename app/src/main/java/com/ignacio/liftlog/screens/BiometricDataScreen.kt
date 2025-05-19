package com.ignacio.liftlog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BiometricDataScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Datos biométricos",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Aquí podrás consultar y registrar información como peso, grasa corporal, IMC y más métricas relacionadas con tu progreso físico.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

