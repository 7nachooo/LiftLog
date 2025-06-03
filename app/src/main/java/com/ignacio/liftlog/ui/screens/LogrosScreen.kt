package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LogrosScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("🏆 Tus logros", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        LogroItem("🔥 2 semanas seguidas entrenando")
        LogroItem("📅 5 entrenamientos este mes")
        LogroItem("💯 Has superado tu objetivo de calorías 3 días seguidos")
        LogroItem("🌙 Has dormido al menos 7h durante 4 días consecutivos")
    }
}

@Composable
fun LogroItem(titulo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = titulo, fontSize = 16.sp)
        }
    }
}

