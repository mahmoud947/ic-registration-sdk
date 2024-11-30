package com.example.icr_domain.usecases.validation

import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_domain.R

class ValidateEmailUseCase : BaseIOUseCase<String, ValidateResultState> {
    override fun invoke(input: String): ValidateResultState {
        if (input.trim().isEmpty()) {
            return ValidateResultState(messageResId = R.string.this_field_can_not_be_empty)
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            return ValidateResultState(messageResId = R.string.invalid_email_format)
        }

        return ValidateResultState(
            success = true
        )
    }
}