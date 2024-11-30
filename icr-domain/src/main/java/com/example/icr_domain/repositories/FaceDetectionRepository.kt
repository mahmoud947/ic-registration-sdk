package com.example.icr_domain.repositories

import android.graphics.Bitmap

interface FaceDetectionRepository {
    suspend fun detect(bitmap: Bitmap): Float
}