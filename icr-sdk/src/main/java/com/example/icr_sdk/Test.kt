package com.example.icr_sdk

import com.example.icr_core.error.ICRException

interface ICRSDKTListener {
    fun onValidationSuccess()
    fun onValidationFailure(exception: Exception)
}