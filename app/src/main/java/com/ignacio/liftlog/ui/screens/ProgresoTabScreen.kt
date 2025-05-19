package com.ignacio.liftlog.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ignacio.liftlog.screens.EntrenamientoDetalleActivity
import com.ignacio.liftlog.screens.RegistroEntrenamientoActivity

data class Entrenamiento(
    val fecha: String,
    val titulo: String,
    val duracion: String,
    val calorias: String,
    val lugar: String,
    val iconoLugar: String
)

@Composable
fun ProgresoTabScreen() {
    val context = LocalContext.current
    var semanaSeleccionada by remember { mutableStateOf("Semana actual") }

    val semanas = listOf("Semana actual", "Semana pasada", "Hace 2 semanas")
    val entrenamientos = remember {
        listOf(
            Entrenamiento("10 abril 2025", "Pierna y glúteo", "55 min", "610 kcal", "Gimnasio", "🏋️"),
            Entrenamiento("12 abril 2025", "Cardio HIIT", "30 min", "420 kcal", "Casa", "🏠"),
            Entrenamiento("14 abril 2025", "Espalda y bíceps", "80 min", "590 kcal", "Gimnasio", "🏋️"),
            Entrenamiento("18 abril 2025", "Pecho y tríceps", "75 min", "570 kcal", "Gimnasio", "🏋️")
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    context.startActivity(Intent(context, RegistroEntrenamientoActivity::class.java))
                },
                containerColor = Color(0xFFFF6D00)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir entrenamiento", tint = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Historial de entrenamientos",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Selector de semana
            var expanded by remember { mutableStateOf(false) }
            Box {
                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(semanaSeleccionada)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    semanas.forEach { semana ->
                        DropdownMenuItem(
                            text = { Text(semana) },
                            onClick = {
                                semanaSeleccionada = semana
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Total esta semana: 4 entrenos, 4h, 2190 kcal",
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(6.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(entrenamientos) { entrenamiento ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(entrenamiento.fecha, fontWeight = FontWeight.Bold)
                                Text(entrenamiento.titulo, fontWeight = FontWeight.SemiBold)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("⏱ ${entrenamiento.duracion}", fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text("🔥 ${entrenamiento.calorias}", fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text("${entrenamiento.iconoLugar} ${entrenamiento.lugar}", fontSize = 12.sp)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    context.startActivity(Intent(context, EntrenamientoDetalleActivity::class.java))
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00))
                            ) {
                                Text("Ver detalles", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}


