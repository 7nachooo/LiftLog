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
fun EditProfileScreen(
    initialName: String,
    initialEmail: String,
    initialWeight: String,
    onSave: (String, String, String) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf(TextFieldValue(initialName)) }
    var email by remember { mutableStateOf(TextFieldValue(initialEmail)) }
    var weight by remember { mutableStateOf(TextFieldValue(initialWeight)) }

    var emailError by remember { mutableStateOf(false) }
    var weightError by remember { mutableStateOf(false) }

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
            value = weight,
            onValueChange = {
                weight = it
                weightError = false
            },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = weightError
        )
        if (weightError) {
            Text("Peso no válido", color = MaterialTheme.colorScheme.error)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    val emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
                    val weightVal = weight.text.toFloatOrNull()
                    var valid = true

                    if (!emailRegex.matches(email.text.trim())) {
                        emailError = true
                        valid = false
                    }
                    if (weightVal == null || weightVal <= 0) {
                        weightError = true
                        valid = false
                    }

                    if (valid) {
                        onSave(name.text.trim(), email.text.trim(), weight.text.trim())
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
