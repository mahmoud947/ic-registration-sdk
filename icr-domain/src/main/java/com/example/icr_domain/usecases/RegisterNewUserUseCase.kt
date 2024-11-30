package com.example.icr_domain.usecases

import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_core.base.Resource
import com.example.icr_core.error.DatabaseInsertException
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RegisterNewUserUseCase(
    private val authRepository: AuthRepository
) : BaseIOUseCase<ICRUser, Flow<Resource<Long>>> {
    override fun invoke(input: ICRUser): Flow<Resource<Long>> = flow {
        emit(Resource.Loading)
        val result = authRepository.insertUser(input)
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(DatabaseInsertException(it)))
    }
}
