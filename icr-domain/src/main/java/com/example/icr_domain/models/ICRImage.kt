package com.example.icr_domain.models

import android.net.Uri

data class ICRImage(
    val id: Int? = null,
    val uri: Uri,
    val userId: Int
)
