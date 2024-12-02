package com.example.icr_core.listner

import androidx.compose.ui.graphics.Color
import com.example.icr_core.base.ICRResult
import com.example.icr_core.enums.Language

interface ICRSDKCoreListener {
    fun onValidationSuccess(icrResult: ICRResult)
    fun onValidationFailure(exception: Exception)
    fun onCancelByUser()
}

object ICRSDKManager {
    var listener: ICRSDKCoreListener? = null
    var language: Language? = null

    var isDarkMode: Boolean? = null

    var primary: Color? = null
    var secondary: Color? = null
    var tertiary: Color? = null
    var background: Color? = null

    var lightPrimary: Color? = null
    var lightSecondary: Color? = null
    var lightTertiary: Color? = null
    var lightBackground: Color? = null
}