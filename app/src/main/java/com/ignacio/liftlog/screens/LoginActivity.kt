package com.ignacio.liftlog.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.ignacio.liftlog.R
import com.ignacio.liftlog.ui.components.AuthButton
import com.ignacio.liftlog.ui.components.DividerWithText
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var launcher: androidx.activity.result.ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
            return
        }

        // ✅ Configurar Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { authResult ->
                        if (authResult.isSuccessful) {
                            startActivity(Intent(this, DashboardActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Error al autenticar con Google", Toast.LENGTH_SHORT).show()
                        }
                    }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        setContent {
            MaterialTheme {
                LoginScreen(
                    onGoogleSignInClick = { launcher.launch(googleSignInClient.signInIntent) }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(onGoogleSignInClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(1.dp))

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
                onClick = onGoogleSignInClick
            )
            Spacer(modifier = Modifier.height(8.dp))
            AuthButton(
                text = "Continuar con Apple",
                icon = painterResource(id = R.drawable.ic_apple_logo),
                onClick = { /* Acción futura */ }
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
                    if (email.isNotBlank() && password.isNotBlank()) {
                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                                    context.startActivity(Intent(context, DashboardActivity::class.java))
                                } else {
                                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                    } else {
                        Toast.makeText(context, "Por favor, rellena ambos campos", Toast.LENGTH_SHORT).show()
                    }
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
