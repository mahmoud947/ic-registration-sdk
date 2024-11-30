package com.example.icr_ui.screen.smileDetection

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.icr_ui.components.ICRCircularCameraPreview

@Composable
fun SmileDetectionScreen() {
    val context = LocalContext.current
    var showCamera by remember { mutableStateOf(false) }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                showCamera = isGranted
            })

    val permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
    LaunchedEffect(permissionCheck) {
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    ICRCircularCameraPreview { bitmap ->

    }
}