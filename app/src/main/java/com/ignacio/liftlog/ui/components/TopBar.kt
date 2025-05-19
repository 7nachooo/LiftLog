package com.ignacio.liftlog.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(userName: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = "Hola, $userName 👋",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

