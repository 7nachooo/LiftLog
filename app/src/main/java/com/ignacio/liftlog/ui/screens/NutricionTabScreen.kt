package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NutricionTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Nutrición 🍎",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Aquí podrás llevar el seguimiento de tus comidas, nutrientes y hábitos alimenticios.",
            style = MaterialTheme.typography.bodyMedium
        )


    }
}
