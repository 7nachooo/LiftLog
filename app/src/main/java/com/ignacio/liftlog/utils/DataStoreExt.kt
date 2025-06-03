package com.ignacio.liftlog.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "entrenamientos_prefs")
