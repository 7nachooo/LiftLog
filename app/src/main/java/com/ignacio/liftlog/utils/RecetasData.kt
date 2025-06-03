package com.ignacio.liftlog.utils

import com.ignacio.liftlog.R
import com.ignacio.liftlog.models.Receta

object RecetasData {
    val recetas = listOf(
        Receta("Tostadas con aguacate", 320, 10, 5, 10, "desayuno", R.drawable.receta_aguacate),
        Receta("Ensalada de quinoa", 450, 18, 10, 15, "almuerzo", R.drawable.receta_quinoa),
        Receta("Tortilla de claras", 200, 20, 7, 8, "desayuno", R.drawable.receta_tortilla),
        Receta("Salmón al horno", 540, 25, 15, 20, "cena", R.drawable.receta_salmon),
        Receta("Yogur con frutos", 150, 8, 3, 5, "snack", R.drawable.receta_yogur)
    )
}
