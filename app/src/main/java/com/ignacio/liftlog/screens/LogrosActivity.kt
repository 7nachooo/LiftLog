package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LogrosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LogrosScreen()
            }
        }
    }
}

data class Logro(val titulo: String, val descripcion: String, val desbloqueado: Boolean)

@Composable
fun LogrosScreen() {
    val logros = listOf(
        Logro("2 semanas seguidas entrenando", "Has mantenido la racha durante 14 días 💪", true),
        Logro("Entrenamiento nocturno", "Has entrenado después de las 22:00 🌙", false),
        Logro("1000 kcal en un día", "Has quemado más de 1000 kcal en una sesión 🔥", true),
        Logro("Primer entrenamiento", "Has registrado tu primer entrenamiento 🚀", true)
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("🏆 Logros", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(logros) { logro ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (logro.desbloqueado) Color(0xFFDFFFD6) else Color(0xFFEEEEEE)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(logro.titulo, fontSize = 18.sp, color = if (logro.desbloqueado) Color(0xFF388E3C) else Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(logro.descripcion, fontSize = 14.sp, color = Color.DarkGray)
                    }
                }
            }
        }
    }
}
