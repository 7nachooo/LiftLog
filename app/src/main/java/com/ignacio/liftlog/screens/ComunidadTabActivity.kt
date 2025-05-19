package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

class ComunidadTabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                com.ignacio.liftlog.ui.screens.ComunidadTabScreen()
            }
        }
    }
}

@Composable
fun ComunidadTabScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Pantalla de Comunidad 🤝", style = MaterialTheme.typography.headlineMedium)
    }
}
