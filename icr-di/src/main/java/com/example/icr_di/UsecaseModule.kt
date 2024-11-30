package com.example.icr_di

import com.example.icr_domain.usecases.auth.RegisterNewUserUseCase
import com.example.icr_domain.usecases.faceDetection.DetectSmileUseCase
import com.example.icr_domain.usecases.validation.FormValidationUseCase
import com.example.icr_domain.usecases.validation.ValidateConfirmPasswordUseCase
import com.example.icr_domain.usecases.validation.ValidateEmailUseCase
import com.example.icr_domain.usecases.validation.ValidatePasswordUseCase
import com.example.icr_domain.usecases.validation.ValidatePhoneNumberUseCase
import com.example.icr_domain.usecases.validation.ValidateTextUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<RegisterNewUserUseCase> { RegisterNewUserUseCase(get()) }
    factory<ValidateTextUseCase> { ValidateTextUseCase() }
    factory<ValidatePhoneNumberUseCase> { ValidatePhoneNumberUseCase() }
    factory<ValidateEmailUseCase> { ValidateEmailUseCase() }
    factory<ValidatePasswordUseCase> { ValidatePasswordUseCase() }
    factory<ValidateConfirmPasswordUseCase> { ValidateConfirmPasswordUseCase() }
    factory<FormValidationUseCase> { FormValidationUseCase(get(), get(), get(), get(), get()) }
    factory<DetectSmileUseCase> { DetectSmileUseCase(get()) }
}
