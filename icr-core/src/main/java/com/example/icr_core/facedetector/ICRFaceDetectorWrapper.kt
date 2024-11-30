package com.example.icr_core.facedetector

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class ICRFaceDetectorWrapper : ICRFaceDetector {
    private val detector by lazy {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        FaceDetection.getClient(options)
    }

    override fun detectFace(bitmap: Bitmap, callback: (Float?) -> Unit) {
        val image = InputImage.fromBitmap(bitmap, 0)
        detector.process(image)
            .addOnSuccessListener { faces ->
                val smileProbability = faces.maxOfOrNull { it.smilingProbability ?: 0f }
                callback(smileProbability)
            }
            .addOnFailureListener {
                callback(null)
            }
    }
}