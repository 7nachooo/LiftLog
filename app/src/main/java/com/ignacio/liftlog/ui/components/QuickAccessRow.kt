package com.ignacio.liftlog.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun QuickAccessRow(
    onNutritionClick: () -> Unit,
    onSleepClick: () -> Unit,
    onStatsClick: () -> Unit,
    onCommunityClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        QuickAccessItem("Nutrición", Icons.Default.Restaurant, onNutritionClick)
        QuickAccessItem("Sueño", Icons.Default.Hotel, onSleepClick)
        QuickAccessItem("Estadísticas", Icons.Default.BarChart, onStatsClick)
        QuickAccessItem("Comunidad", Icons.Default.Group, onCommunityClick)
    }
}

@Composable
fun QuickAccessItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(icon, contentDescription = label, tint = Color.Black)
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp, color = Color.Black)
    }
}

