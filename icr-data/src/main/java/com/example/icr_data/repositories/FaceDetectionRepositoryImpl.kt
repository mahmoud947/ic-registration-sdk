package com.example.icr_data.repositories

import android.graphics.Bitmap
import com.example.icr_core.error.NoFaceDetectedException
import com.example.icr_core.facedetector.ICRFaceDetector
import com.example.icr_domain.repositories.FaceDetectionRepository
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FaceDetectionRepositoryImpl(
    private val faceDetector: ICRFaceDetector
) : FaceDetectionRepository {
    override suspend fun detect(bitmap: Bitmap): Float = suspendCoroutine { continuation ->
        faceDetector.detectFace(bitmap) { smileProbability ->
            if (smileProbability != null) {
                continuation.resume(smileProbability)
            } else {
                continuation.resumeWithException(NoFaceDetectedException())
            }
        }
    }
}