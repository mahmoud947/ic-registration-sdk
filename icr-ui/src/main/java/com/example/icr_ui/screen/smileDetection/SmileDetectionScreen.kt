package com.example.icr_ui.screen.smileDetection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.icr_ui.components.ICRCircularCameraPreview

@Composable
fun SmileDetectionScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        ICRCircularCameraPreview { bitmap ->

        }
    }
}