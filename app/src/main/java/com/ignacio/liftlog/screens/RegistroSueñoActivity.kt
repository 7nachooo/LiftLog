package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class RegistroSueñoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroSuenoScreen()
        }
    }
}

@Composable
fun RegistroSuenoScreen() {
    var horaAcostarse by remember { mutableStateOf("") }
    var horaDespertar by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFF9FAFB)),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrar sueño 😴", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = horaAcostarse,
            onValueChange = { horaAcostarse = it },
            label = { Text("Hora de acostarse (HH:mm)") },
            singleLine = true
        )

        OutlinedTextField(
            value = horaDespertar,
            onValueChange = { horaDespertar = it },
            label = { Text("Hora de despertarse (HH:mm)") },
            singleLine = true
        )

        Button(
            onClick = {
                try {
                    val start = LocalTime.parse(horaAcostarse, timeFormatter)
                    val end = LocalTime.parse(horaDespertar, timeFormatter)
                    val duration = if (end.isAfter(start)) {
                        Duration.between(start, end)
                    } else {
                        Duration.between(start, end.plusHours(24))
                    }
                    val horas = duration.toHours()
                    val minutos = duration.minusHours(horas).toMinutes()
                    duracion = "${horas}h ${minutos}min"
                } catch (e: Exception) {
                    duracion = "Formato inválido"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Calcular duración", color = Color.White)
        }

        if (duracion.isNotBlank()) {
            Text("🕒 Has dormido: $duracion", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // Aquí podrías guardar los datos en almacenamiento local si lo deseas
            },
            enabled = duracion.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Guardar", color = Color.White)
        }
    }
}
