package com.ignacio.liftlog.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ignacio.liftlog.screens.LogrosActivity

@Composable
fun EstadisticasTabScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "📊 Estadísticas generales",
            fontSize = 22.sp,
            style = MaterialTheme.typography.titleLarge
        )

        EstadisticaBox("Entrenamientos este mes", "12 sesiones")
        EstadisticaBox("Calorías quemadas", "7.300 kcal")
        EstadisticaBox("Sueño promedio", "7h 12min")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, LogrosActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver logros 🏆")
        }
    }
}

@Composable
fun EstadisticaBox(titulo: String, valor: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDEDED))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(titulo, fontSize = 14.sp, color = Color.DarkGray)
            Text(valor, fontSize = 18.sp, color = Color.Black)
        }
    }
}
