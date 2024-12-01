package com.example.icr_core.facedetector

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

private const val TAG = "ICRFaceDetectorWrapper"
class ICRFaceDetectorWrapper : ICRFaceDetector {
    private val detector by lazy {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .setMinFaceSize(0.6f)
            .enableTracking()
            .build()

        FaceDetection.getClient(options)
    }

    override fun detectFace(bitmap: Bitmap, callback: (Float?) -> Unit) {
        val image = InputImage.fromBitmap(bitmap, 0)
        detector.process(image)
            .addOnSuccessListener { faces ->
                val smileProbability = faces.maxOfOrNull { it.smilingProbability ?: 0f }
                Log.d(TAG, "detectFace: smileProbability = $smileProbability")
                callback(smileProbability)
            }
            .addOnFailureListener {
                callback(null)
            }
    }
}