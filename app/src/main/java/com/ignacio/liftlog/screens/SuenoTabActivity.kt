package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

class SuenoTabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SuenoTabScreen()
            }
        }
    }
}

@Composable
fun SuenoTabScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Pantalla de Sueño 😴", style = MaterialTheme.typography.headlineMedium)
    }
}
