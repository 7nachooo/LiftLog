package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.ignacio.liftlog.models.Entrenamiento
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import com.ignacio.liftlog.models.LugarEntreno


@Composable
fun EntrenamientoDetalleScreen(entreno: Entrenamiento) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Detalle del entrenamiento", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Fecha: ${entreno.fecha}", fontSize = 16.sp)
        Text("Tipo: ${entreno.tipo}", fontSize = 16.sp)
        Text("Duración: ${entreno.duracion} min", fontSize = 16.sp)
        Text("Calorías quemadas: ${entreno.calorias}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            val icon: ImageVector = if (entreno.lugar == LugarEntreno.GIMNASIO) {
                Icons.Default.FitnessCenter
            } else {
                Icons.Default.Home
            }
            Image(imageVector = icon, contentDescription = "Lugar del entrenamiento")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Lugar: ${entreno.lugar.name.lowercase().replaceFirstChar { it.uppercase() }}")
        }
    }
}
