package com.example.icr_ui.screen.smileDetection

import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.StringRes
import com.example.icr_core.base.ViewEvent
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.base.ViewState
import com.example.icr_domain.models.ICRUserWithImage


class SmileDetectionContract {
    sealed class Event : ViewEvent {
        data class OnStartSmileDetection(val bitmap: Bitmap) : Event()
        data class SaveImage(val bitmap: Bitmap) : Event()
        data class OnSetUserId(val userId: Long) : Event()
        data object OnCancel: Event()
    }

    sealed class SideEffect : ViewSideEffect {
        data class Finish(val userDetails: ICRUserWithImage?) : SideEffect()
        data object Cancel: SideEffect()
        data object Exit: SideEffect()
    }

    data class State(
        override val loading: Boolean = false,
        val smileProgress: Int = 0,
        val imageUri: Uri? = null,
        val stopSmileDetection: Boolean = false,
        @StringRes
        val screenMessage: Int? = null,
        val userId: Long = 0
    ) : ViewState
}