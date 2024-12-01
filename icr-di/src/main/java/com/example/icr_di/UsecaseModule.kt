package com.example.icr_di

import com.example.icr_domain.usecases.user.InsertNewUserUseCase
import com.example.icr_domain.usecases.faceDetection.DetectSmileUseCase
import com.example.icr_domain.usecases.user.GetUserDataByUserIdUseCase
import com.example.icr_domain.usecases.user.InsertUserImageUseCase
import com.example.icr_domain.usecases.validation.FormValidationUseCase
import com.example.icr_domain.usecases.validation.ValidateConfirmPasswordUseCase
import com.example.icr_domain.usecases.validation.ValidateEmailUseCase
import com.example.icr_domain.usecases.validation.ValidatePasswordUseCase
import com.example.icr_domain.usecases.validation.ValidatePhoneNumberUseCase
import com.example.icr_domain.usecases.validation.ValidateTextUseCase
import org.koin.dsl.factory
import org.koin.dsl.module

val useCaseModule = module {
    factory<InsertNewUserUseCase> { InsertNewUserUseCase(get()) }
    factory<ValidateTextUseCase> { ValidateTextUseCase() }
    factory<ValidatePhoneNumberUseCase> { ValidatePhoneNumberUseCase() }
    factory<ValidateEmailUseCase> { ValidateEmailUseCase() }
    factory<ValidatePasswordUseCase> { ValidatePasswordUseCase() }
    factory<ValidateConfirmPasswordUseCase> { ValidateConfirmPasswordUseCase() }
    factory<FormValidationUseCase> { FormValidationUseCase(get(), get(), get(), get(), get()) }
    factory<DetectSmileUseCase> { DetectSmileUseCase(get()) }
    factory<InsertUserImageUseCase> { InsertUserImageUseCase(get()) }
    factory<GetUserDataByUserIdUseCase> { GetUserDataByUserIdUseCase(get()) }
}
