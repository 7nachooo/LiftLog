package com.ignacio.liftlog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun RegistroEntrenamientoScreen() {
    var tipo by remember { mutableStateOf(TextFieldValue("")) }
    var duracion by remember { mutableStateOf(TextFieldValue("")) }
    var calorias by remember { mutableStateOf(TextFieldValue("")) }
    var lugar by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Registrar nuevo entrenamiento", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo de entrenamiento") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = duracion, onValueChange = { duracion = it }, label = { Text("Duración (min)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = calorias, onValueChange = { calorias = it }, label = { Text("Calorías quemadas") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = lugar, onValueChange = { lugar = it }, label = { Text("Lugar") }, modifier = Modifier.fillMaxWidth())

        Button(
            onClick = { /* Guardar datos y volver atrás */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar entrenamiento")
        }
    }
}
