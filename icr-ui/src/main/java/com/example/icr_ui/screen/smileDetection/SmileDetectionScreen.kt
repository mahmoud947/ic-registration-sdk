package com.example.icr_ui.screen.smileDetection

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.utils.Margin
import com.example.icr_ui.components.ICRCircularCameraPreview
import com.example.icr_ui.components.SmileProgressIndicator
import com.example.icr_ui.screen.registration.RegistrationContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SmileDetectionScreen(
    modifier: Modifier = Modifier,
    uiState: SmileDetectionContract.State = SmileDetectionContract.State(),
    onEvent: (SmileDetectionContract.Event) -> Unit = {},
    sideEffect: Flow<ViewSideEffect> = emptyFlow(),
    navController: NavController = rememberNavController()
) {
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

    Scaffold { innerPadding ->

        Column(
            modifier = modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ICRCircularCameraPreview { bitmap ->
                onEvent(SmileDetectionContract.Event.OnStartSmileDetection(bitmap))
            }
            Margin(20.dp)
            SmileProgressIndicator(
                progress = uiState.smileProgress
            )
        }

    }


}