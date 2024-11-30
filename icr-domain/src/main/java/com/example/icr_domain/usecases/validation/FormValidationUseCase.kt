package com.example.icr_domain.usecases.validation

data class FormValidationUseCase(
    val validateTextUseCase: ValidateTextUseCase,
    val validatePhoneNumber: ValidatePhoneNumberUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val validateConfirmPassword: ValidateConfirmPasswordUseCase
)
