package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.ignacio.liftlog.ui.screens.RegistroEntrenamientoScreen
import com.ignacio.liftlog.utils.EntrenamientosViewModel

class RegistroEntrenamientoActivity : ComponentActivity() {
    private lateinit var viewModel: EntrenamientosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear ViewModel
        viewModel = ViewModelProvider(this)[EntrenamientosViewModel::class.java]

        setContent {
            MaterialTheme {
                RegistroEntrenamientoScreen(viewModel)
            }
        }
    }
}
