package com.example.icr_ui.screen.smileDetection

import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.StringRes
import com.example.icr_core.base.ViewEvent
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.base.ViewState


class SmileDetectionContract {
    sealed class Event : ViewEvent {
        data class OnStartSmileDetection(val bitmap: Bitmap) : Event()
        data class SaveImage(val bitmap: Bitmap) : Event()
    }

    sealed class SideEffect : ViewSideEffect {
        data object Finish : SideEffect()
    }

    data class State(
        override val loading: Boolean = false,
        val smileProgress: Int = 0,
        val imageUri: Uri? = null,
        val stopSmileDetection: Boolean = false,
        @StringRes
        val screenMessage: Int? = null
    ) : ViewState
}