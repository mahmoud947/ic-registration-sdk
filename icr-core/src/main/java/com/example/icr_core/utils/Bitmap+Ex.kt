package com.example.icr_core.utils

import android.graphics.Bitmap
import android.graphics.Matrix

fun Bitmap.resize(newWidth: Int, newHeight: Int): Bitmap {
    return Bitmap.createScaledBitmap(this, newWidth, newHeight, false)
}

 fun Bitmap.rotateBitmap(rotationDegrees: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(rotationDegrees) }
    return Bitmap.createBitmap(
        this,
        0,
        0,
        this.width,
        this.height,
        matrix,
        true
    )
}