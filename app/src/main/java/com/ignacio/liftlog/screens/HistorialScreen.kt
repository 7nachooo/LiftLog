package com.ignacio.liftlog.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistorialScreen() {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Configuración ⚙️",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Ajusta tus preferencias de usuario.",
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium
        )

        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { notificationsEnabled = !notificationsEnabled }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Notificaciones")
            Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { darkModeEnabled = !darkModeEnabled }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Modo oscuro")
            Switch(checked = darkModeEnabled, onCheckedChange = { darkModeEnabled = it })
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* TODO: implementar logout */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Cerrar sesión", color = MaterialTheme.colorScheme.onError)
        }
    }
}
