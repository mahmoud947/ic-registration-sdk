package com.example.icr_ui.screen.smileDetection

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.icr_core.base.ICRApplicationViewModel
import com.example.icr_core.base.Resource
import com.example.icr_domain.usecases.faceDetection.DetectSmileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okio.IOException
import java.io.File
import java.io.FileOutputStream

class SmileDetectionViewModel(
    private val detectSmileUseCase: DetectSmileUseCase,
    private val appContext: Application
) : ICRApplicationViewModel<SmileDetectionContract.Event, SmileDetectionContract.State>(
    appContext
) {
    override fun setInitialState(): SmileDetectionContract.State = SmileDetectionContract.State()

    override fun handleEvents(event: SmileDetectionContract.Event) {
        when (event) {
            is SmileDetectionContract.Event.OnStartSmileDetection -> detectSmile(event.bitmap)
            is SmileDetectionContract.Event.SaveImage -> saveBitmapToNonMediaStorage(event.bitmap)
        }
    }

    private fun detectSmile(bitmap: Bitmap) = launchCoroutine(Dispatchers.Default) {
        detectSmileUseCase(bitmap).flowOn(Dispatchers.Default)
            .collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        setState { copy(loading = false) }
                    }

                    is Resource.Loading -> {
                        setState { copy(loading = true) }
                    }

                    is Resource.Success -> {
                        setState { copy(loading = false, smileProgress = resource.data) }
                        setEffect { SmileDetectionContract.SideEffect.NavigateToNextScreen }
                    }
                }
            }
    }

    fun saveBitmapToNonMediaStorage(bitmap: Bitmap) {
        launchCoroutine(Dispatchers.IO) {
            try {
                val rotatedBitmap = rotateBitmap(bitmap, -90f)
                val directory = File(appContext.filesDir, "SmileDetection")
                if (!directory.exists() && !directory.mkdirs()) {
                    throw IOException("Failed to create directory: ${directory.absolutePath}")
                }

                val fileName = "smile_${System.currentTimeMillis()}.png"
                val file = File(directory, fileName)

                FileOutputStream(file).use { outputStream ->
                    if (!rotatedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                        throw IOException("Failed to compress and save bitmap")
                    }
                }

                val uri = FileProvider.getUriForFile(
                    appContext,
                    "${appContext.packageName}.fileprovider",
                    file
                )

                withContext(Dispatchers.Main) {
                    setState {
                        copy(imageUri = uri)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                   // setEffect { SmileDetectionContract.SideEffect.NavigateToNextScreen }
                }
            }
        }
    }



    private fun rotateBitmap(bitmap: Bitmap, rotationDegrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(rotationDegrees) }
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}