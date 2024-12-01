package com.example.icr_ui.screen.smileDetection

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.icr_core.base.OnEffect
import com.example.icr_core.base.ShowMessage
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.listner.ICRSDKManager
import com.example.icr_core.utils.Margin
import com.example.icr_domain.R
import com.example.icr_ui.components.ICRBottomSheet
import com.example.icr_ui.components.ICRCircularCameraPreview
import com.example.icr_ui.components.ICRLottiAnimation
import com.example.icr_ui.components.ICRText
import com.example.icr_ui.theme.medium
import com.example.icr_ui.utils.toResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmileDetectionScreen(
    modifier: Modifier = Modifier,
    uiState: SmileDetectionContract.State = SmileDetectionContract.State(),
    onEvent: (SmileDetectionContract.Event) -> Unit = {},
    sideEffect: Flow<ViewSideEffect> = emptyFlow(),
    userId: Long,
) {
    val context = LocalContext.current
    var showCamera by remember { mutableStateOf(false) }

    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var bottomSheet by remember { mutableStateOf(ShowMessage()) }

    val bottomSheetSate = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

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
    LaunchedEffect(Unit) {
        onEvent(SmileDetectionContract.Event.OnSetUserId(userId))
    }

    sideEffect.OnEffect { effect ->
        when (effect) {
            is SmileDetectionContract.SideEffect.Finish -> {
                effect.userDetails?.let {
                    ICRSDKManager.listener?.onValidationSuccess(
                        it.toResult()
                    )
                    (context as? ComponentActivity)?.finish()
                }
            }

            is ShowMessage -> {
                bottomSheet = effect
                scope.launch {
                    bottomSheetSate.show()
                    isBottomSheetOpen = true
                }
            }

            SmileDetectionContract.SideEffect.Cancel -> {
                ICRSDKManager.listener?.onCancelByUser()
                (context as? ComponentActivity)?.finish()
            }
        }

    }

    Scaffold { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ICRCircularCameraPreview(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                uiState.smileProgress,
                stopSmileDetection = uiState.stopSmileDetection,
                imageUri = uiState.imageUri,
                onSmileDetected = {
                    onEvent(SmileDetectionContract.Event.SaveImage(it))
                }
            ) { bitmap ->
                onEvent(SmileDetectionContract.Event.OnStartSmileDetection(bitmap))
            }
            Margin(medium)
            uiState.screenMessage?.let {
                Margin(medium)
                ICRText(text = stringResource(id = it), style = MaterialTheme.typography.bodyLarge)
            }

            if (isBottomSheetOpen) {
                ICRBottomSheet(
                    sheetState = bottomSheetSate,
                    modifier = Modifier.fillMaxWidth(),
                    positiveActionLabel = stringResource(R.string.ok),
                    title = stringResource(bottomSheet.title ?: R.string.ok),
                    subTitle = stringResource(bottomSheet.message ?: R.string.ok),
                    onDismiss = {
                        isBottomSheetOpen = false
                        scope.launch {
                            bottomSheetSate.hide()
                        }
                    },
                    onPositive = {
                        isBottomSheetOpen = false
                        scope.launch {
                            bottomSheetSate.hide()
                        }
                        bottomSheet.positiveAction()
                    },
                    iconContent = {
                        ICRLottiAnimation(
                            modifier = Modifier.size(100.dp),
                            rowLottie = bottomSheet.icon ?: R.raw.warning_animation
                        )
                    }
                )
            }
        }

    }


}

