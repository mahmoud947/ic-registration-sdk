package com.example.icr_core.base

import android.net.Uri

data class ICRResult(
    val userId: Int,
    val userName: String,
    val userPassword: String,
    val userPhoneNumber: String,
    val userEmail: String,
    val imageId: Int?,
    val imageUrl: Uri?
)
