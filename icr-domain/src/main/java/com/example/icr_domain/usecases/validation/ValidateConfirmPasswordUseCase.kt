package com.example.icr_domain.usecases.validation

import com.example.icr_core.base.BaseIOUseCase
import com.example.icr_domain.R
data class ValidateConfirmPasswordUseCaseIN(
    val password: String,
    val confirmPassword: String
)
class ValidateConfirmPasswordUseCase() : BaseIOUseCase<ValidateConfirmPasswordUseCaseIN, ValidateResultState> {
    override fun invoke(input: ValidateConfirmPasswordUseCaseIN): ValidateResultState {
        if (input.confirmPassword.trim().isEmpty()) {
            return ValidateResultState(messageResId = R.string.this_field_can_not_be_empty)
        }
        if (input.confirmPassword != input.password) {
            return ValidateResultState(messageResId = R.string.passwords_do_not_match)
        }
        return ValidateResultState(
            success = true
        )
    }
}