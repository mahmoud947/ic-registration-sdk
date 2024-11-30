package com.example.icr_di

import com.example.icr_domain.usecases.RegisterNewUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<RegisterNewUserUseCase> { RegisterNewUserUseCase(get()) }
}