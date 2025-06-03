package com.ignacio.liftlog.models

data class Entrenamiento(
    val fecha: String,
    val tipo: String,
    val duracion: Int,
    val calorias: Int,
    val lugar: LugarEntreno
)
