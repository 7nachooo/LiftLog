package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.ignacio.liftlog.ui.theme.LiftLogTheme
import androidx.compose.ui.draw.clip


class SuenoTabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiftLogTheme {
                PantallaSueno()
            }
        }
    }
}

@Composable
fun PantallaSueno() {
    val graficoDatos = entryModelOf(7f, 6.5f, 8f, 7f, 5.5f, 7.5f, 6f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("😴 Resumen de sueño", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("Dormiste 6h 45min anoche", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 6.75f / 8f,
                    color = Color(0xFF10B981),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("Objetivo: 8 horas", fontSize = 14.sp)
            }
        }

        Text("📈 Gráfico semanal de sueño", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            Chart(
                chart = lineChart(),
                model = graficoDatos
            )
        }

        Button(
            onClick = { /* TODO: Navegar a pantalla de registro de sueño */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Registrar sueño 🛌", color = Color.White)
        }
    }
}
