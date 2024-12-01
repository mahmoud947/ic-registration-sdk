package com.example.icr_ui.screen.smileDetection

import android.graphics.Bitmap
import com.example.icr_core.base.ICRViewModel
import com.example.icr_core.base.Resource
import com.example.icr_domain.usecases.faceDetection.DetectSmileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

class SmileDetectionViewModel(
    private val detectSmileUseCase: DetectSmileUseCase
): ICRViewModel<SmileDetectionContract.Event, SmileDetectionContract.State>() {
    override fun setInitialState(): SmileDetectionContract.State = SmileDetectionContract.State()

    override fun handleEvents(event: SmileDetectionContract.Event) {
        when(event){
            is SmileDetectionContract.Event.OnStartSmileDetection -> detectSmile(event.bitmap)
            is SmileDetectionContract.Event.SaveImage ->{}
        }
    }

    private fun detectSmile(bitmap: Bitmap) = launchCoroutine(Dispatchers.Default) {
        detectSmileUseCase(bitmap).flowOn(Dispatchers.Default)
            .collectLatest { resource ->
                when(resource){
                    is Resource.Error -> { setState { copy(loading = false) }}
                    is Resource.Loading -> { setState { copy(loading = true) }}
                    is Resource.Success -> {
                        setState { copy(loading = false, smileProgress = resource.data) }
                        setEffect { SmileDetectionContract.SideEffect.NavigateToNextScreen }
                    }
                }
            }
    }
}