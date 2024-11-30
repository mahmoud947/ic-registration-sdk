package com.example.icr_domain.usecases.faceDetection

import android.graphics.Bitmap
import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_core.base.Resource
import com.example.icr_core.error.NoFaceDetectedException
import com.example.icr_core.error.UnknownException
import com.example.icr_domain.repositories.FaceDetectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DetectSmileUseCase(
    private val faceDetectionRepository: FaceDetectionRepository
) : BaseIOUseCase<Bitmap, Flow<Resource<Int>>> {
    override fun invoke(input: Bitmap): Flow<Resource<Int>> = flow {
        emit(Resource.Loading)
        val smileProbability = faceDetectionRepository.detect(input)
        val smileProgress = (smileProbability * 100).toInt()
        emit(Resource.Success(smileProgress))
    }.catch {
        when (it) {
            is NoFaceDetectedException -> {
                emit(Resource.Error(NoFaceDetectedException()))
            }
            else -> {
                emit(Resource.Error(UnknownException()))
            }
        }

    }
}