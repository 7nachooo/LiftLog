package com.ignacio.liftlog.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    var darkMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Ajustes",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        SettingItem("🔒 Cambiar contraseña") {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }

        SettingItem("🌐 Idioma") {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }

        SettingItem("✏️ Unidades (kg/lbs)") {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }

        SettingItem("🔔 Notificaciones") {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }

        SettingItem("📄 Términos y privacidad") {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("🌙 Modo oscuro", fontSize = 16.sp)
            Switch(
                checked = darkMode,
                onCheckedChange = { darkMode = it }
            )
        }

        Button(
            onClick = {
                Toast.makeText(context, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2))
        ) {
            Text("🔒 Cerrar sesión", color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SettingItem(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
