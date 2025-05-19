package com.ignacio.liftlog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogrosScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tus logros 🏆",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Has desbloqueado 6 de 12 logros",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )

        Divider()

        // Aquí podrías renderizar una lista de logros con sus estados
        repeat(3) { i ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Logro ${i + 1}", fontWeight = MaterialTheme.typography.bodyLarge.fontWeight)
                    Text("Descripción del logro desbloqueado")
                }
            }
        }
    }
}
