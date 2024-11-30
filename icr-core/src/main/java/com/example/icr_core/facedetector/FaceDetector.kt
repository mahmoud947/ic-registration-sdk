package com.example.icr_core.facedetector

import android.graphics.Bitmap

interface FaceDetector {
    fun detectFace(bitmap: Bitmap, callback: (Float?) -> Unit)
}