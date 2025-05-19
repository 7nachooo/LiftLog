package com.ignacio.liftlog.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconWithLabel(Icons.Default.Restaurant, "Nutrición")
        IconWithLabel(Icons.Default.Hotel, "Sueño")
        IconWithLabel(Icons.Default.BarChart, "Estadísticas")
        IconWithLabel(Icons.Default.Group, "Comunidad")
    }
}

@Composable
fun IconWithLabel(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = label)
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}
