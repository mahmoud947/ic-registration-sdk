package com.example.icr_sdk.module

import android.net.Uri

data class ICRSdkResult(
    val userId: Long,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val userName: String,
    val imageUri: Uri?
)
