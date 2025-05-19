package com.ignacio.liftlog.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressBarSection(progress: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            progress = progress / 100f,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = Color(0xFF00AEEF),
            trackColor = Color.LightGray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$progress%",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
    }
}

