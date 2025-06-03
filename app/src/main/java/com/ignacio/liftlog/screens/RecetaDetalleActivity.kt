package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.ignacio.liftlog.ui.screens.RecetaDetalleScreen

class RecetaDetalleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val calorias = intent.getIntExtra("calorias", 0)
        val proteinas = intent.getIntExtra("proteinas", 0)
        val grasas = intent.getIntExtra("grasas", 0)
        val categoria = intent.getStringExtra("categoria") ?: ""
        val imagenRes = intent.getIntExtra("imagenRes", 0)

        setContent {
            MaterialTheme {
                RecetaDetalleScreen(
                    nombre = nombre,
                    calorias = calorias,
                    proteinas = proteinas,
                    grasas = grasas,
                    categoria = categoria,
                    imagenRes = imagenRes
                )
            }
        }
    }
}


