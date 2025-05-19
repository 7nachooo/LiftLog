package com.ignacio.liftlog.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeeklyProgressChart(values: List<Float>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp)
    ) {
        val barWidth = size.width / (values.size * 2)
        val maxHeight = size.height
        values.forEachIndexed { index, value ->
            val left = index * barWidth * 2
            val top = maxHeight - (value / 100f) * maxHeight
            val height = (value / 100f) * maxHeight

            drawRect(
                color = Color(0xFF00AEEF),
                topLeft = Offset(x = left, y = top),
                size = androidx.compose.ui.geometry.Size(barWidth, height)
            )
        }
    }
}


