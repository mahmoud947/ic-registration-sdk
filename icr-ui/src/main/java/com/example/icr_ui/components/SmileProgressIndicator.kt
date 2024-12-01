package com.example.icr_ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SmileProgressIndicator(progress: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(300.dp)
            .clip(CircleShape)
            .border(4.dp, Color.Gray, CircleShape)
    ) {
        CircularProgressIndicator(
            progress = progress / 100f, // Convert to 0-1 scale
            modifier = Modifier.size(300.dp),
            strokeWidth = 8.dp
        )
        Text(
            text = "$progress%",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}