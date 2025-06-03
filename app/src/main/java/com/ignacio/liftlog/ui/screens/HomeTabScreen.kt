package com.ignacio.liftlog.ui.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ignacio.liftlog.screens.ComunidadTabActivity
import com.ignacio.liftlog.screens.EstadisticasTabActivity
import com.ignacio.liftlog.screens.NutricionTabActivity
import com.ignacio.liftlog.screens.SuenoTabActivity
import com.ignacio.liftlog.utils.EntrenamientoData
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import android.annotation.SuppressLint


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeTabScreen(navController: NavController) {
    val context = LocalContext.current
    val entrenamientos = EntrenamientoData.entrenamientos
    val sesionesActuales = entrenamientos.size
    val sesionesObjetivo = 5
    val progreso = (sesionesActuales.toFloat() / sesionesObjetivo).coerceAtMost(1f)
    val caloriasTotales = entrenamientos.sumOf { it.calorias }

    val chartData = entryModelOf(2f, 4f, 1f, 5f, 3f, 2f, 4f)
    val diasSemana = listOf("L", "M", "X", "J", "V", "S", "D")
    val bottomAxis = rememberBottomAxis(
        valueFormatter = { value, _ -> diasSemana.getOrNull(value.toInt()) ?: "" }
    )

    var userName by remember { mutableStateOf("usuario") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            FirebaseFirestore.getInstance().collection("usuarios").document(userId).get()
                .addOnSuccessListener { document ->
                    val fullName = document.getString("nombre") ?: "usuario"
                    userName = fullName.split(" ").firstOrNull() ?: fullName
                    isLoading = false
                }
                .addOnFailureListener { e ->
                    Log.e("HomeTabScreen", "Error fetching user name", e)
                    isLoading = false
                }
        } else {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (isLoading) {
            Text("Cargando...", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        } else {
            Text("Hola, $userName! 👋", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text("Progreso mensual", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = progreso,
                color = Color(0xFF10B981),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("$sesionesActuales / $sesionesObjetivo sesiones", fontSize = 14.sp, modifier = Modifier.align(Alignment.End))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricBox(
                icon = "🏋️",
                titulo = "Entrenamientos",
                valor = "$sesionesActuales / $sesionesObjetivo sesiones",
                modifier = Modifier.weight(1f)
            )
            MetricBox(
                icon = "🔥",
                titulo = "Calorías",
                valor = "$caloriasTotales / 2300 kcal",
                modifier = Modifier.weight(1f)
            )
            MetricBox(
                icon = "😴",
                titulo = "Sueño",
                valor = "7h 15min / 8h",
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = {
                navController.navigate("registro_entrenamiento")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("Registrar entrenamiento 🏋️", color = Color.White)
        }

        Text("Accesos rápidos", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuickAccess("🍽️", "Nutrición") {
                context.startActivity(Intent(context, NutricionTabActivity::class.java))
            }
            QuickAccess("🛌", "Sueño") {
                context.startActivity(Intent(context, SuenoTabActivity::class.java))
            }
            QuickAccess("📊", "Estadísticas") {
                context.startActivity(Intent(context, EstadisticasTabActivity::class.java))
            }
            QuickAccess("👥", "Comunidad") {
                context.startActivity(Intent(context, ComunidadTabActivity::class.java))
            }
        }

        Text("📈 Gráfico semanal de entrenamientos", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            Chart(
                chart = columnChart(),
                model = chartData,
                bottomAxis = bottomAxis
            )
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MetricBox(icon: String, titulo: String, valor: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .height(100.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(icon, fontSize = 18.sp)
            Text(titulo, fontSize = 12.sp, fontWeight = FontWeight.Medium, maxLines = 1)
            Text(valor, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, maxLines = 2)
        }
    }
}

@Composable
fun QuickAccess(icon: String, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable(onClick = onClick)
    ) {
        Text(icon, fontSize = 24.sp)
        Text(label, fontSize = 12.sp)
    }
}
