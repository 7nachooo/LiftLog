package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ignacio.liftlog.models.Comida
import com.ignacio.liftlog.utils.ComidaData
import com.ignacio.liftlog.utils.ComidaStorage

@Composable
fun NuevaNutricionTabScreen() {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var calorias by remember { mutableStateOf(TextFieldValue("")) }

    val totalCalorias = remember { derivedStateOf { ComidaData.comidas.sumOf { it.calorias } } }


    LaunchedEffect(Unit) {
        if (!ComidaData.cargado) {
            val comidasGuardadas = ComidaStorage.cargarComidas(context)
            ComidaData.comidas.clear()
            ComidaData.comidas.addAll(comidasGuardadas)
            ComidaData.cargado = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("🍽️", fontSize = MaterialTheme.typography.titleLarge.fontSize)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Nutrición", fontSize = MaterialTheme.typography.titleLarge.fontSize, color = Color(0xFF388E3C))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Calorías totales: ${totalCalorias.value} kcal")

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del alimento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = calorias,
            onValueChange = { calorias = it },
            label = { Text("Calorías") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val kcal = calorias.text.toIntOrNull() ?: 0
                if (nombre.text.isNotBlank() && kcal > 0) {
                    val nuevaComida = Comida(nombre.text, kcal)
                    ComidaData.comidas.add(nuevaComida)
                    ComidaStorage.guardarComidas(context, ComidaData.comidas)
                    nombre = TextFieldValue("")
                    calorias = TextFieldValue("")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
        ) {
            Text("Añadir comida", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Listado de comidas 🍎")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(ComidaData.comidas) { comida ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(comida.nombre)
                            Text("${comida.calorias} kcal")
                        }
                        IconButton(onClick = {
                            ComidaData.comidas.remove(comida)
                            ComidaStorage.guardarComidas(context, ComidaData.comidas)
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar comida")
                        }
                    }
                }
            }
        }
    }
}

