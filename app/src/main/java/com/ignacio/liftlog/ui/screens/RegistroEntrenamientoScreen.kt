package com.ignacio.liftlog.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ignacio.liftlog.models.Entrenamiento
import com.ignacio.liftlog.models.LugarEntreno
import com.ignacio.liftlog.utils.EntrenamientosViewModel
import org.threeten.bp.LocalDate

@Composable
fun RegistroEntrenamientoScreen(
    viewModel: EntrenamientosViewModel = viewModel()
) {
    var tipo by remember { mutableStateOf(TextFieldValue("")) }
    var duracion by remember { mutableStateOf(TextFieldValue("")) }
    var calorias by remember { mutableStateOf(TextFieldValue("")) }
    var lugarSeleccionado by remember { mutableStateOf(LugarEntreno.GIMNASIO) }
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Registrar nuevo entrenamiento", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = tipo,
            onValueChange = { tipo = it },
            label = { Text("Tipo de entrenamiento") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duración (min)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = calorias,
            onValueChange = { calorias = it },
            label = { Text("Calorías quemadas") },
            modifier = Modifier.fillMaxWidth()
        )

        Box {
            TextField(
                value = lugarSeleccionado.name,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                label = { Text("Lugar") },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                LugarEntreno.values().forEach { lugar ->
                    DropdownMenuItem(
                        text = { Text(lugar.name) },
                        onClick = {
                            lugarSeleccionado = lugar
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                val duracionInt = duracion.text.toIntOrNull() ?: 0
                val caloriasInt = calorias.text.toIntOrNull() ?: 0

                if (tipo.text.isNotBlank() && duracionInt > 0 && caloriasInt > 0) {
                    val nuevo = Entrenamiento(
                        tipo = tipo.text,
                        duracion = duracionInt,
                        calorias = caloriasInt,
                        lugar = lugarSeleccionado,
                        fecha = LocalDate.now().toString()
                    )
                    viewModel.addEntrenamiento(nuevo)

                    tipo = TextFieldValue("")
                    duracion = TextFieldValue("")
                    calorias = TextFieldValue("")

                    Toast.makeText(context, "Entrenamiento guardado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar entrenamiento")
        }
    }
}
