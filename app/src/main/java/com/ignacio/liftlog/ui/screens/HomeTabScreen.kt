package com.ignacio.liftlog.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ignacio.liftlog.ui.components.*
import com.ignacio.liftlog.screens.*

@Composable
fun HomeTabScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Saludo inicial
        GreetingSection(userName = "Nacho")

        // Barra de progreso mensual
        ProgressBarSection(progress = 80)

        // Tarjetas informativas: sesiones, calorías, sueño
        ProgressSection(
            sessions = "3 / 5 sesiones",
            calories = "1800 kcal / 2300 kcal",
            sleep = "7h 15min / 8h"
        )

        // Botón registrar entrenamiento
        RegisterWorkoutButton(
            onClick = {
                context.startActivity(Intent(context, RegistroEntrenamientoActivity::class.java))
            }
        )

        // Accesos directos con navegación
        QuickAccessRow(
            onNutritionClick = {
                context.startActivity(Intent(context, NutricionTabScreenActivity::class.java))
            },
            onSleepClick = {
                context.startActivity(Intent(context, SuenoTabScreenActivity::class.java))
            },
            onStatsClick = {
                context.startActivity(Intent(context, EstadisticasTabScreenActivity::class.java))
            },
            onCommunityClick = {
                context.startActivity(Intent(context, ComunidadTabScreenActivity::class.java))
            }
        )

        // Gráfico de progreso semanal
        WeeklyProgressChart(values = listOf(80f, 100f, 90f, 70f, 85f, 95f, 60f))
    }
}

