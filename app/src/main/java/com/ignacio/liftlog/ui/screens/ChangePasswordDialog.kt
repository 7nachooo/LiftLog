package com.ignacio.liftlog.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChangePasswordDialog(
    onDismiss: () -> Unit,
    onPasswordChanged: (success: Boolean, message: String) -> Unit
) {
    val context = LocalContext.current
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cambiar contraseña") },
        text = {
            Column {
                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text("Contraseña actual") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Nueva contraseña") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
                OutlinedTextField(
                    value = confirmNewPassword,
                    onValueChange = { confirmNewPassword = it },
                    label = { Text("Confirmar nueva contraseña") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
                if (errorMsg != null) {
                    Text(errorMsg!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
                }
                if (isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    errorMsg = null
                    if (newPassword.length < 6) {
                        errorMsg = "La nueva contraseña debe tener al menos 6 caracteres"
                        return@TextButton
                    }
                    if (newPassword != confirmNewPassword) {
                        errorMsg = "Las contraseñas no coinciden"
                        return@TextButton
                    }
                    if (currentPassword.isBlank()) {
                        errorMsg = "Debes ingresar la contraseña actual"
                        return@TextButton
                    }

                    isLoading = true
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user != null && user.email != null) {
                        val credential = EmailAuthProvider.getCredential(user.email!!, currentPassword)
                        user.reauthenticate(credential).addOnCompleteListener { reauthTask ->
                            if (reauthTask.isSuccessful) {
                                user.updatePassword(newPassword).addOnCompleteListener { updateTask ->
                                    isLoading = false
                                    if (updateTask.isSuccessful) {
                                        onPasswordChanged(true, "Contraseña actualizada con éxito")
                                    } else {
                                        onPasswordChanged(false, updateTask.exception?.localizedMessage ?: "Error al actualizar contraseña")
                                    }
                                }
                            } else {
                                isLoading = false
                                errorMsg = "Contraseña actual incorrecta"
                            }
                        }
                    } else {
                        isLoading = false
                        errorMsg = "Usuario no autenticado"
                    }
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
