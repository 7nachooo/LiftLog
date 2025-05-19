package com.ignacio.liftlog.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Intent
import com.ignacio.liftlog.R
import com.ignacio.liftlog.ui.components.AuthButton
import com.ignacio.liftlog.ui.components.DividerWithText

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(1.dp)) // relleno superior

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "LiftLog",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

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
                label = { Text("Email o nombre de usuario") },
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
                    val intent = Intent(context, DashboardActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground)
            ) {
                Text("Continuar", color = MaterialTheme.colorScheme.background)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                Toast.makeText(context, "Recuperar contraseña no implementado", Toast.LENGTH_SHORT).show()
            }) {
                Text("¿Olvidaste la contraseña?")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                context.startActivity(Intent(context, RegisterActivity::class.java))
            }) {
                Text("Nuevo en LiftLog? Regístrate")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Al continuar, aceptas nuestros Términos de servicio y Política de privacidad.",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}

