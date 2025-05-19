package com.ignacio.liftlog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment

@Composable
fun EntrenamientoDetalleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Detalles del Entrenamiento", style = MaterialTheme.typography.headlineSmall)

        Text("📅 Fecha: 18 abril 2025", fontSize = 16.sp)
        Text("🏋️ Tipo: Pecho y tríceps", fontSize = 16.sp)
        Text("⏱ Duración: 75 minutos", fontSize = 16.sp)
        Text("🔥 Calorías quemadas: 570 kcal", fontSize = 16.sp)
        Text("📍 Lugar: Gimnasio", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ejercicios realizados", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("- Press banca: 4 sets x 12 reps")
        Text("- Fondos en paralelas: 3 sets x 10 reps")
        Text("- Aperturas con mancuernas: 3 sets x 15 reps")
    }
}

