package com.ignacio.liftlog.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ignacio.liftlog.R

data class Post(val nombre: String, val mensaje: String, val avatarRes: Int)

@Composable
fun ComunidadTabScreen() {
    val posts = remember {
        mutableStateListOf(
            Post("Lucía", "¡Hoy entrené pierna y estoy que no puedo andar! 😂", R.drawable.avatar1),
            Post("Carlos", "Reto de 30 días de abdominales... Día 12 completo 💪", R.drawable.avatar2),
            Post("Marta", "Me encantó la receta fit de salmón, 100% recomendada!", R.drawable.avatar3)
        )
    }
    val likes = remember { mutableStateMapOf<Post, Boolean>() }

    var showDialog by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(posts) { post ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = post.avatarRes),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(post.nombre, fontWeight = FontWeight.Bold)
                            Text(post.mensaje, fontSize = 14.sp)
                        }
                        IconButton(onClick = {
                            likes[post] = !(likes[post] ?: false)
                        }) {
                            Icon(
                                imageVector = if (likes[post] == true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Like",
                                tint = if (likes[post] == true) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Text("+", color = Color.White, fontSize = 24.sp)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    if (nombre.isNotBlank() && mensaje.isNotBlank()) {
                        val avatarList = listOf(
                            R.drawable.avatar1,
                            R.drawable.avatar2,
                            R.drawable.avatar3
                        )
                        val avatarRandom = avatarList.random()
                        posts.add(Post(nombre, mensaje, avatarRandom))
                        nombre = ""
                        mensaje = ""
                        showDialog = false
                    }
                }) {
                    Text("Publicar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Nuevo entrenamiento 🏋️") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = mensaje,
                        onValueChange = { mensaje = it },
                        label = { Text("¿Qué hiciste hoy?") },
                        maxLines = 4
                    )
                }
            }
        )
    }
}
