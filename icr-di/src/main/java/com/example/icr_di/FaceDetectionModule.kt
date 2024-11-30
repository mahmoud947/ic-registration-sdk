package com.example.icr_di

import com.example.icr_core.facedetector.ICRFaceDetector
import com.example.icr_core.facedetector.ICRFaceDetectorWrapper
import org.koin.dsl.module

val faceDetectionModule = module {
    factory<ICRFaceDetector> { ICRFaceDetectorWrapper() }
}
