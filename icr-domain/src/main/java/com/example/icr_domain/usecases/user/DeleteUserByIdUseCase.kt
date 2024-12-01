package com.example.icr_domain.usecases.user

import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_core.base.Resource
import com.example.icr_core.error.DatabaseInsertException
import com.example.icr_domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DeleteUserByIdUseCase(
    private val authRepository: AuthRepository
) : BaseIOUseCase<Int, Flow<Resource<Unit>>> {
    override fun invoke(input: Int): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        val result = authRepository.deleteUserId(input)
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(DatabaseInsertException(it)))
    }
}
