package com.ignacio.liftlog.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ignacio.liftlog.R
import com.ignacio.liftlog.screens.SettingsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.navigation.NavController
import com.ignacio.liftlog.ui.screens.EditProfileExtendedScreen


@Composable
fun PerfilTabScreen(navController: NavController) {
    val context = LocalContext.current
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
    var userData by remember { mutableStateOf<Map<String, Any>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var isEditing by remember { mutableStateOf(false) }

    var profilePicUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        profilePicUri = uri
        Toast.makeText(context, "Foto seleccionada (simulada)", Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(userId) {
        FirebaseFirestore.getInstance()
            .collection("usuarios")
            .document(userId)
            .get()
            .addOnSuccessListener { doc ->
                userData = doc.data
                isLoading = false
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
    }

    if (isEditing) {
        EditProfileExtendedScreen(
            initialName = userData?.get("nombre")?.toString() ?: "",
            initialEmail = userData?.get("email")?.toString() ?: "",
            initialEdad = userData?.get("edad")?.toString() ?: "",
            initialAltura = userData?.get("altura")?.toString() ?: "",
            initialPeso = userData?.get("peso")?.toString() ?: "",
            onSave = { nombre, email, edad, altura, peso ->
                val updateData = mapOf(
                    "nombre" to nombre,
                    "email" to email,
                    "edad" to (edad.toIntOrNull() ?: 0),
                    "altura" to (altura.toIntOrNull() ?: 0),
                    "peso" to (peso.toIntOrNull() ?: 0)
                )
                FirebaseFirestore.getInstance()
                    .collection("usuarios")
                    .document(userId)
                    .update(updateData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Perfil actualizado", Toast.LENGTH_SHORT).show()
                        userData = updateData
                        isEditing = false
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Error al actualizar", Toast.LENGTH_LONG).show()
                    }
            },
            onCancel = { isEditing = false }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Perfil", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Ajustes",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { navController.navigate("settings") }
                )
            }

            // Foto de perfil
            Box(
                modifier = Modifier.clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (profilePicUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(profilePicUri),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(100.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = userData?.get("nombre")?.toString()?.take(1)?.uppercase() ?: "?",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (isLoading) {
                CircularProgressIndicator()
            } else if (userData != null) {
                Text(userData!!["nombre"]?.toString() ?: "Nombre", style = MaterialTheme.typography.titleLarge)
                Text(userData!!["email"]?.toString() ?: "Correo", style = MaterialTheme.typography.bodyMedium)

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Edad: ${userData!!["edad"] ?: "?"} años")
                        Text("Altura: ${userData!!["altura"]} cm")
                        Text("Peso: ${userData!!["peso"]} kg")
                    }
                }

                Button(
                    onClick = { isEditing = true },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Editar perfil")
                }
            } else {
                Text("No se encontraron datos del usuario", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

