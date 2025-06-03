package com.ignacio.liftlog.utils

import androidx.compose.runtime.mutableStateListOf
import com.ignacio.liftlog.models.Comida

object ComidaData {
    val comidas = mutableStateListOf<Comida>()
    var cargado = false
}

