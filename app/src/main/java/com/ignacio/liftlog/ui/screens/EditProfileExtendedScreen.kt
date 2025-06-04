package com.ignacio.liftlog.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun EditProfileExtendedScreen(
    initialName: String,
    initialEmail: String,
    initialEdad: String,
    initialAltura: String,
    initialPeso: String,
    onSave: (String, String, String, String, String) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf(TextFieldValue(initialName)) }
    var email by remember { mutableStateOf(TextFieldValue(initialEmail)) }
    var edad by remember { mutableStateOf(TextFieldValue(initialEdad)) }
    var altura by remember { mutableStateOf(TextFieldValue(initialAltura)) }
    var peso by remember { mutableStateOf(TextFieldValue(initialPeso)) }

    var emailError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }
    var alturaError by remember { mutableStateOf(false) }
    var pesoError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Editar Perfil", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = emailError
        )
        if (emailError) {
            Text("Email no válido", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = edad,
            onValueChange = {
                edad = it
                edadError = false
            },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = edadError
        )
        if (edadError) {
            Text("Edad no válida", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = altura,
            onValueChange = {
                altura = it
                alturaError = false
            },
            label = { Text("Altura (cm)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = alturaError
        )
        if (alturaError) {
            Text("Altura no válida", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = peso,
            onValueChange = {
                peso = it
                pesoError = false
            },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = pesoError
        )
        if (pesoError) {
            Text("Peso no válido", color = MaterialTheme.colorScheme.error)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    var valid = true
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                        emailError = true
                        valid = false
                    }
                    if (edad.text.toIntOrNull() == null) {
                        edadError = true
                        valid = false
                    }
                    if (altura.text.toIntOrNull() == null) {
                        alturaError = true
                        valid = false
                    }
                    if (peso.text.toIntOrNull() == null) {
                        pesoError = true
                        valid = false
                    }

                    if (valid) {
                        onSave(
                            name.text.trim(),
                            email.text.trim(),
                            edad.text.trim(),
                            altura.text.trim(),
                            peso.text.trim()
                        )
                        Toast.makeText(context, "Perfil guardado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Corrige los errores", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }

            Button(
                onClick = { onCancel() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Cancelar", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}
