package com.example.icr_core.listner

import com.example.icr_core.error.ICRException

interface ICRSDKListener {
    fun onValidationSuccess()
    fun onValidationFailure(exception: Exception)
}

object ICRSDKManager {
    var listener: ICRSDKListener? = null
}