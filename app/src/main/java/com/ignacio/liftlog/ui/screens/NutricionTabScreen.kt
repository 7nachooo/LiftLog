package com.ignacio.liftlog.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import com.ignacio.liftlog.models.Receta
import com.ignacio.liftlog.utils.RecetasData
import com.ignacio.liftlog.screens.RecetaDetalleActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutricionTabScreen() {
    val context = LocalContext.current
    var categoriaSeleccionada by remember { mutableStateOf("todos") }
    val categorias = listOf("todos", "desayuno", "almuerzo", "cena", "snack")

    val recetasFiltradas = remember(categoriaSeleccionada) {
        if (categoriaSeleccionada == "todos") RecetasData.recetas
        else RecetasData.recetas.filter { it.categoria == categoriaSeleccionada }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Nutrición 🥗", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = { /* abrir perfil */ }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Perfil")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Filtros
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            categorias.forEach { cat ->
                FilterChip(
                    selected = cat == categoriaSeleccionada,
                    onClick = { categoriaSeleccionada = cat },
                    label = { Text(cat.replaceFirstChar { it.uppercase() }) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(recetasFiltradas) { receta ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, RecetaDetalleActivity::class.java)
                            intent.putExtra("nombre", receta.nombre)
                            intent.putExtra("calorias", receta.calorias)
                            intent.putExtra("proteinas", receta.proteinas)
                            intent.putExtra("grasas", receta.grasas)
                            intent.putExtra("categoria", receta.categoria)
                            intent.putExtra("imagenRes", receta.imagenRes)
                            context.startActivity(intent)
                        }

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = receta.imagenRes),
                            contentDescription = receta.nombre,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(receta.nombre, fontWeight = FontWeight.Bold)
                            Text("${receta.calorias} kcal • ${receta.proteinas}g proteínas")
                            Text("⏱ ${receta.duracionMin} min", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

