package com.example.icr_ui.screen.smileDetection

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.icr_core.base.ICRApplicationViewModel
import com.example.icr_core.base.Resource
import com.example.icr_core.base.ShowMessage
import com.example.icr_core.error.NoFaceDetectedException
import com.example.icr_domain.usecases.faceDetection.DetectSmileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import com.example.icr_domain.R
import com.example.icr_domain.models.ICRImage
import com.example.icr_domain.usecases.user.GetUserDataByUserIdUseCase
import com.example.icr_domain.usecases.user.InsertUserImageUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import java.io.IOException

class SmileDetectionViewModel(
    private val detectSmileUseCase: DetectSmileUseCase,
    private val saveUserImageUseCase: InsertUserImageUseCase,
    private val getUserDataByUserIdUseCase: GetUserDataByUserIdUseCase,
    private val appContext: Application
) : ICRApplicationViewModel<SmileDetectionContract.Event, SmileDetectionContract.State>(
    appContext
) {
    override fun setInitialState(): SmileDetectionContract.State = SmileDetectionContract.State()

    override fun handleEvents(event: SmileDetectionContract.Event) {
        when (event) {
            is SmileDetectionContract.Event.OnStartSmileDetection -> detectSmile(event.bitmap)
            is SmileDetectionContract.Event.SaveImage -> saveBitmapToNonMediaStorage(event.bitmap)
            is SmileDetectionContract.Event.OnSetUserId -> setState { copy(userId = event.userId) }
        }
    }

    @OptIn(FlowPreview::class)
    private fun detectSmile(bitmap: Bitmap) = launchCoroutine(Dispatchers.Default) {
        detectSmileUseCase(bitmap)
            .flowOn(Dispatchers.Default)
            .distinctUntilChanged()
            .debounce(400)
            .collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        when (resource.exception) {
                            is NoFaceDetectedException -> {
                                setState {
                                    copy(screenMessage = R.string.center_face_message)
                                }
                            }

                            else -> {
                                setEffect {
                                    ShowMessage(
                                        title = R.string.error_title,
                                        message = R.string.general_error_message,
                                    )
                                }
                            }
                        }
                    }

                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val message =
                            if (resource.data != 0f) R.string.start_smile_message else null
                        setState {
                            copy(
                                loading = false,
                                smileProgress = resource.data.toInt(),
                                screenMessage = message
                            )
                        }
                    }
                }
            }
    }

    fun saveBitmapToNonMediaStorage(bitmap: Bitmap) {
        launchCoroutine(Dispatchers.IO) {
            try {
                setState {
                    copy(loading = true)
                }
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
                        copy(stopSmileDetection = true, imageUri = uri, loading = false)
                    }
                    setEffect {
                        ShowMessage(
                            title = R.string.successfully,
                            message = R.string.authentication_success_message,
                            icon = R.raw.success,
                            positiveAction = {
                                saveUserImage(userId = viewState.value.userId, imageUri = uri)
                            }
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    setEffect {
                        ShowMessage(
                            title = R.string.error_title,
                            message = R.string.general_error_message,
                        )
                    }
                }
            }
        }
    }

    private fun saveUserImage(userId: Long, imageUri: Uri) = launchCoroutine(Dispatchers.IO) {
        val image = ICRImage(
            userId = userId.toInt(),
            uri = imageUri,
        )
        saveUserImageUseCase(input = image)
            .flowOn(Dispatchers.IO)
            .collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        setEffect {
                            ShowMessage(
                                title = R.string.error_title,
                                message = R.string.general_error_message,
                            )
                        }
                    }

                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        getUserDetails(userId)
                    }
                }

            }
    }

    private fun getUserDetails(userId: Long) = launchCoroutine(Dispatchers.IO) {
        getUserDataByUserIdUseCase(input = userId.toInt())
            .flowOn(Dispatchers.IO)
            .collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        setEffect {
                            ShowMessage(
                                title = R.string.error_title,
                                message = R.string.general_error_message,
                            )
                        }
                    }

                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        setEffect { SmileDetectionContract.SideEffect.Finish(resource.data) }
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