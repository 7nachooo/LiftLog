package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecetaDetalleScreen(
    nombre: String,
    calorias: Int,
    proteinas: Int,
    grasas: Int,
    categoria: String,
    imagenRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Detalle de Receta", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imagenRes),
            contentDescription = nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("🍽 $nombre", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Text("🔥 Calorías: $calorias kcal")
        Text("💪 Proteínas: $proteinas g")
        Text("🥑 Grasas: $grasas g")
        Text("📂 Categoría: $categoria")
    }
}

