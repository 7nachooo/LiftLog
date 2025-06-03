package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ignacio.liftlog.models.Entrenamiento
import com.ignacio.liftlog.utils.EntrenamientosViewModel
import com.ignacio.liftlog.models.LugarEntreno


@Composable
fun ProgresoTabScreen(navController: NavController, viewModel: EntrenamientosViewModel) {
    val entrenamientos by viewModel.entrenamientos.collectAsState()

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate("registro_entrenamiento") }) {
            Text("+", fontSize = 24.sp)
        }
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            item {
                Text("Historial de entrenamientos", style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(16.dp))
            }
            items(entrenamientos) { entrenamiento ->
                EntrenoItem(entrenamiento)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun EntrenoItem(entreno: Entrenamiento) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(entreno.fecha, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp)
            Text("Duración: ${entreno.duracion} min")
            Text("Calorías: ${entreno.calorias}")
        }
        Icon(
            imageVector = if (entreno.lugar == LugarEntreno.GIMNASIO) Icons.Default.FitnessCenter else Icons.Default.Home,
            contentDescription = "Lugar del entrenamiento"
        )
    }
}

