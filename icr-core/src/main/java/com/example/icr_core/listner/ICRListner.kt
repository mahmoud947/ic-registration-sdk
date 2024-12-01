package com.example.icr_core.listner

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

    fun cleanUp() {
        listener = null
        language = null
    }
}