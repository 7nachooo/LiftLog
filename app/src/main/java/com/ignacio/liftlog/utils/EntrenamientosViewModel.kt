package com.ignacio.liftlog.utils

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ignacio.liftlog.models.Entrenamiento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.asStateFlow
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

class EntrenamientosViewModel(private val context: Context) : ViewModel() {
    private val _entrenamientos = MutableStateFlow<List<Entrenamiento>>(emptyList())
    val entrenamientos: StateFlow<List<Entrenamiento>> = _entrenamientos.asStateFlow()

    private val Context.dataStore by preferencesDataStore("entrenamientos")

    private val KEY_ENTRENAMIENTOS = stringPreferencesKey("entrenamientos_json")

    init {
        loadEntrenamientos()
    }

    private fun loadEntrenamientos() {
        viewModelScope.launch {
            context.dataStore.data.collect { prefs ->
                val json = prefs[KEY_ENTRENAMIENTOS] ?: "[]"
                val listType = object : TypeToken<List<Entrenamiento>>() {}.type
                val lista: List<Entrenamiento> = Gson().fromJson(json, listType)
                _entrenamientos.value = lista
            }
        }
    }

    fun addEntrenamiento(entrenamiento: Entrenamiento) {
        viewModelScope.launch {
            val nuevaLista = _entrenamientos.value.toMutableList()
            nuevaLista.add(entrenamiento)
            _entrenamientos.value = nuevaLista
            saveEntrenamientos(nuevaLista)
        }
    }

    private suspend fun saveEntrenamientos(lista: List<Entrenamiento>) {
        val json = Gson().toJson(lista)
        context.dataStore.edit { prefs ->
            prefs[KEY_ENTRENAMIENTOS] = json
        }
    }
}
