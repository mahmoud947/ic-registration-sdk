package com.example.icr_domain.usecases.validation

import androidx.annotation.StringRes

data class ValidateResultState(
    val success: Boolean = false,
    @StringRes val messageResId: Int? = null
)
