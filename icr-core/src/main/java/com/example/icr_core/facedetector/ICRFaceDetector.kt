package com.example.icr_core.facedetector

import android.graphics.Bitmap

interface ICRFaceDetector {
    fun detectFace(bitmap: Bitmap, callback: (Float?) -> Unit)
}