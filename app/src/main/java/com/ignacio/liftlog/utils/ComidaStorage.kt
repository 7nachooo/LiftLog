package com.ignacio.liftlog.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ignacio.liftlog.models.Comida

object ComidaStorage {
    private const val PREFS_NAME = "comidas_prefs"
    private const val KEY_COMIDAS = "comidas_guardadas"

    fun guardarComidas(context: Context, comidas: List<Comida>) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(comidas)
        prefs.edit().putString(KEY_COMIDAS, json).apply()
    }

    fun cargarComidas(context: Context): List<Comida> {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_COMIDAS, null)
        return if (json != null) {
            val type = object : TypeToken<List<Comida>>() {}.type
            Gson().fromJson(json, type)
        } else emptyList()
    }
}
