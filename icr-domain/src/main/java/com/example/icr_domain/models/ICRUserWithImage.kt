package com.example.icr_domain.models

import android.net.Uri


data class ICRUserWithImage(
    val userId: Int,
    val userEmail: String,
    val imageId: Int?,
    val imageUrl: Uri?,
    val username: String,
    val userPhoneNumber: String,
    val userPassword: String
)