package com.example.icr_domain.usecases.user

import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_core.base.Resource
import com.example.icr_core.error.DatabaseInsertException
import com.example.icr_core.error.GetUserException
import com.example.icr_domain.models.ICRImage
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.models.ICRUserWithImage
import com.example.icr_domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetUserDataByUserIdUseCase(
    private val authRepository: AuthRepository
) : BaseIOUseCase<Int, Flow<Resource<ICRUserWithImage?>>> {
    override fun invoke(input: Int): Flow<Resource<ICRUserWithImage?>> = flow {
        emit(Resource.Loading)
        val result = authRepository.getUserWithImage(input)
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(GetUserException(it)))
    }
}
