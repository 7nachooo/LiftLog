package com.ignacio.liftlog.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.ignacio.liftlog.screens.LoginActivity
import android.content.Intent
import com.ignacio.liftlog.screens.EditProfileActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    var darkMode = remember { mutableStateOf(false) }
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    if (showChangePasswordDialog) {
        ChangePasswordDialog(
            onDismiss = { showChangePasswordDialog = false },
            onPasswordChanged = { success, message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                if (success) showChangePasswordDialog = false
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Ajustes",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        SettingItem("✏️ Editar perfil") {
            navController.navigate("editar_perfil")
        }

        SettingItem("🔒 Cambiar contraseña") {
            showChangePasswordDialog = true
        }
        SettingItem("🌐 Idioma") {
            Toast.makeText(context, "Función en desarrollo", Toast.LENGTH_SHORT).show()
        }
        SettingItem("✏️ Unidades (kg/lbs)") {
            Toast.makeText(context, "Función en desarrollo", Toast.LENGTH_SHORT).show()
        }
        SettingItem("🔔 Notificaciones") {
            Toast.makeText(context, "Función en desarrollo", Toast.LENGTH_SHORT).show()
        }
        SettingItem("📄 Términos y privacidad") {
            Toast.makeText(context, "Función no implementada", Toast.LENGTH_SHORT).show()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        Button(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(context, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, LoginActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
        ) {
            Text("🔒 Cerrar sesión", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SettingItem(text: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
