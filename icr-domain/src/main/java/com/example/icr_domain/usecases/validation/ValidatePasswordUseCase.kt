package com.example.icr_domain.usecases.validation

import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_domain.R


class ValidatePasswordUseCase: BaseIOUseCase<String, ValidateResultState> {
    override fun invoke(input: String): ValidateResultState {
        if (input.trim().isEmpty()) {
            return ValidateResultState(messageResId = R.string.this_field_can_not_be_empty)
        }

        if (input.length < 6) {
            return ValidateResultState(messageResId = R.string.password_cant_be_less_6_chars)
        }

        return ValidateResultState(
            success = true
        )
    }
}