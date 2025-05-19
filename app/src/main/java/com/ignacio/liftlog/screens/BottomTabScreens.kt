package com.ignacio.liftlog.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTabScreen(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomTabScreen("home", Icons.Filled.Home, "Inicio")
    object Progress : BottomTabScreen("progress", Icons.Filled.BarChart, "Progreso")
    object Community : BottomTabScreen("community", Icons.Filled.Group, "Comunidad")
    object Profile : BottomTabScreen("profile", Icons.Filled.Person, "Perfil")

    companion object {
        val items = listOf(Home, Progress, Community, Profile)
    }
}

