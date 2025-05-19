package com.ignacio.liftlog.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
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
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(1.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(48.dp))
            Text("Crear cuenta", fontSize = 32.sp)

            Spacer(modifier = Modifier.height(32.dp))
            AuthButton(
                text = "Continuar con Google",
                icon = painterResource(id = R.drawable.ic_gmail_logo),
                onClick = { /* acción futura */ }
            )

            Spacer(modifier = Modifier.height(8.dp))
            AuthButton(
                text = "Continuar con Apple",
                icon = painterResource(id = R.drawable.ic_apple_logo),
                onClick = { /* acción futura */ }
            )

            Spacer(modifier = Modifier.height(24.dp))
            DividerWithText("o con email")

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                    // Simulamos registro exitoso
                    context.startActivity(Intent(context, MainActivity::class.java))
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
        }

        Text(
            "Al continuar, aceptas nuestros Términos de servicio y Política de privacidad.",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}
