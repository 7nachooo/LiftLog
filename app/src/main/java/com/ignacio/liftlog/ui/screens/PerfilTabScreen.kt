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


@Composable
fun PerfilTabScreen(navController: NavController) {
    val context = LocalContext.current
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
    var userData by remember { mutableStateOf<MutableMap<String, Any>?>(null) }
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
                userData = doc.data?.toMutableMap()
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
                val updateData: Map<String, Any> = mapOf(
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
                        userData = userData ?: mutableMapOf()
                        updateData.forEach { (key, value) ->
                            userData!![key] = value
                        }
                        isEditing = false
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al actualizar: ${e.message}", Toast.LENGTH_LONG).show()
                    }
            },
            onCancel = {
                isEditing = false
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header con icono de ajustes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Perfil",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Ajustes",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.navigate("settings")
                        }
                )

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (profilePicUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(profilePicUri),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                    )
                }
            }

            // Datos usuario
            if (isLoading) {
                Text("Cargando datos del usuario...", fontSize = 14.sp, color = Color.Gray)
            } else if (userData != null) {
                val nombre = userData!!["nombre"]?.toString() ?: "Usuario"
                val email = userData!!["email"]?.toString() ?: "Desconocido"
                val edad = userData!!["edad"]?.toString() ?: "N/A"
                val altura = userData!!["altura"]?.toString() ?: "N/A"
                val peso = userData!!["peso"]?.toString() ?: "N/A"

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.clickable { isEditing = true }
                    )
                    Text(email, fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Edad: $edad años", fontSize = 14.sp)
                    Text("Altura: $altura cm", fontSize = 14.sp)
                    Text("Peso: $peso kg", fontSize = 14.sp)
                }
            } else {
                Text("No se encontraron datos del usuario", color = Color.Red)
            }
        }
    }
}

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
        modifier = Modifier.fillMaxSize().padding(16.dp),
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

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
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
                        onSave(name.text.trim(), email.text.trim(), edad.text.trim(), altura.text.trim(), peso.text.trim())
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
