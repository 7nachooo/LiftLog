package com.ignacio.liftlog.models

data class Receta(
    val nombre: String,
    val calorias: Int,
    val proteinas: Int,
    val grasas: Int,
    val duracionMin: Int,
    val categoria: String,
    val imagenRes: Int
)

