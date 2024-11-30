package com.example.icr_core.base

import com.example.icr_core.error.ICRException


sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(
        val exception: ICRException,
        val code: Int? = null
    ) : Resource<Nothing>()

    data object Loading : Resource<Nothing>()


    fun isSuccessful(): Boolean = this is Success
    fun isFailed(): Boolean = this is Error
    fun isLoading(): Boolean = this is Loading

}