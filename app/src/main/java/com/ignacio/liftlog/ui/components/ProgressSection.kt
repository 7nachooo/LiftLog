package com.ignacio.liftlog.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressSection(sessions: String, calories: String, sleep: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProgressCard(title = "Entrenamientos", value = "3/5")
        ProgressCard(title = "Calorías", value = "1800/2300")
        ProgressCard(title = "Sueño", value = "7h 15min de 8h")
    }
}

