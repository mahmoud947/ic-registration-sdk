package com.example.icr_di

import com.example.icr_ui.screen.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get(), get()) }
}