package com.ignacio.liftlog.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.ignacio.liftlog.MainActivity
import com.ignacio.liftlog.R
import com.ignacio.liftlog.ui.components.AuthButton
import com.ignacio.liftlog.ui.components.DividerWithText

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen() {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text("Crear cuenta", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            text = "Continuar con Google",
            icon = painterResource(id = R.drawable.ic_gmail_logo),
            onClick = { /* funcionalidad futura */ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthButton(
            text = "Continuar con Apple",
            icon = painterResource(id = R.drawable.ic_apple_logo),
            onClick = { /* funcionalidad futura */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        DividerWithText("o con email")

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura (cm)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val emailTrimmed = email.trim()
                val passwordTrimmed = password.trim()

                if (
                    emailTrimmed.isEmpty() || passwordTrimmed.length < 6 ||
                    nombre.isBlank() || edad.isBlank() || altura.isBlank() || peso.isBlank()
                ) {
                    Toast.makeText(
                        context,
                        "Completa todos los campos correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
                if (!emailTrimmed.matches(emailRegex)) {
                    Toast.makeText(context, "Formato de email no válido", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailTrimmed, passwordTrimmed)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            val user = hashMapOf(
                                "email" to emailTrimmed,
                                "nombre" to nombre.trim(),
                                "edad" to edad.trim().toIntOrNull(),
                                "altura" to altura.trim().toIntOrNull(),
                                "peso" to peso.trim().toIntOrNull(),
                                "fechaRegistro" to FieldValue.serverTimestamp()
                            )


                            FirebaseFirestore.getInstance()
                                .collection("usuarios")
                                .document(userId ?: "")
                                .set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "¡Cuenta creada con éxito!", Toast.LENGTH_SHORT).show()
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(context, "Error al guardar datos: ${e.message}", Toast.LENGTH_LONG).show()
                                }

                        } else {
                            Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Al continuar, aceptas nuestros Términos de servicio y Política de privacidad.",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}
