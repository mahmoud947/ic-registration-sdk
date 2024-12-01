package com.example.icr_ui.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.icr_core.utils.resize
import java.util.concurrent.Executors


@SuppressLint("RestrictedApi")
@Composable
fun ICRCircularCameraPreview(
    progress: Int = 70,
    onSmileDetected: (Bitmap) -> Unit ={},
    onFrameCaptured: (Bitmap) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val executor = Executors.newSingleThreadExecutor()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(330.dp)
            .clip(CircleShape)
            .border(4.dp, Color.Gray, CircleShape)
    ) {
        val animatedProgress by animateFloatAsState(
            targetValue = when {
                progress > 60 -> 100f
                else -> progress.toFloat()
            },
            animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing), label = "",

            )

        val animatedColor by animateColorAsState(
            targetValue = when {
                animatedProgress <= 30f -> Color.White
                animatedProgress <= 60f -> lerp(
                    Color.White,
                    Color.Yellow,
                    (animatedProgress - 30f) / 30f
                )

                else -> lerp(Color.Yellow, Color.Green, (animatedProgress - 60f) / 40f)
            },
            animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing), label = ""
        )
        CircularProgressIndicator(
            progress = { animatedProgress / 100f },
            modifier = Modifier.size(330.dp),
            strokeWidth = 30.dp,
            color = animatedColor
        )
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                val imageAnalyzer = ImageAnalysis
                    .Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .setMaxResolution(
                        android.util.Size(320, 240)
                    )
                    .build().apply {
                        setAnalyzer(executor) { imageProxy ->
                            try {
                                val bitmap =
                                    imageProxy.toBitmap().resize(320, 240)
                                onFrameCaptured(bitmap)
                                if (animatedProgress == 100f) {
                                    onSmileDetected(bitmap)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            } finally {
                                imageProxy.close()
                            }
                        }
                    }
                val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalyzer
                )
                preview.surfaceProvider = previewView.surfaceProvider
                previewView
            },
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape)
        )
    }
}


@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun ICRCircularCameraPreviewPreview() {
    ICRCircularCameraPreview(onFrameCaptured = {})
}