package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ignacio.liftlog.ui.screens.SueñoTabScreen

class SuenoTabScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuenoTabScreen()
        }
    }
}
