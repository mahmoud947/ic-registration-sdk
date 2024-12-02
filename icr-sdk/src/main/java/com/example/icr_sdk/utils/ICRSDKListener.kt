package com.example.icr_sdk.utils

import com.example.icr_sdk.module.ICRSdkResult

interface ICRSDKListener {
    fun onValidationSuccess(user: ICRSdkResult)
    fun onValidationFailure(exception: Exception)
    fun onCancelByTheUser()
}