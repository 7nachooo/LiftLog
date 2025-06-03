package com.ignacio.liftlog.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.ignacio.liftlog.ui.screens.EditProfileScreen

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                EditProfileScreen(
                    initialName = "Nacho Arcala",
                    initialEmail = "nacho3@liftlog.com",
                    initialWeight = "72",
                    onSave = { name, email, weight ->

                        finish()
                    },
                    onCancel = {

                        finish()
                    }
                )
            }
        }
    }
}
