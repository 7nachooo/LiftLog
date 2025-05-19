package com.ignacio.liftlog.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext // ✅ ESTE IMPORT ES CLAVE
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ignacio.liftlog.R
import com.ignacio.liftlog.screens.SettingsActivity

@Composable
fun PerfilTabScreen() {
    val context = LocalContext.current
    val userName = "Ignacio Arcalá"
    val userEmail = "ignacio@example.com"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Encabezado con icono de ajustes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Perfil", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Ajustes",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        context.startActivity(Intent(context, SettingsActivity::class.java))
                    }
            )
        }

        // Imagen de perfil
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .clickable {
                        // TODO: Acción para cambiar la imagen de perfil
                    }
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(userName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(userEmail, fontSize = 14.sp, color = Color.Gray)
        }

        // Objetivo mensual
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("🎯 Objetivo mensual", color = Color(0xFF00AEEF), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                Text("8 / 12 sesiones", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(progress = 0.66f)
            }
        }

        Text("¡Estás a solo 4 entrenamientos de alcanzar tu meta! 💪", fontSize = 12.sp)

        // Estadísticas destacadas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF6D00), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PerfilStat("🏋️", "8", "Sesiones")
            PerfilStat("🔥", "2100 kcal", "Prom. diario")
            PerfilStat("😴", "7h 25m", "Sueño semanal")
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}

@Composable
fun PerfilStat(icon: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, fontSize = 20.sp)
        Text(value, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 12.sp)
    }
}

@Composable
fun PerfilAction(icon: String, label: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon)
            Spacer(modifier = Modifier.width(12.dp))
            Text(label)
        }
    }
}
