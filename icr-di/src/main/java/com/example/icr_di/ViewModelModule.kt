package com.example.icr_di

import com.example.icr_ui.screen.registration.RegistrationViewModel
import com.example.icr_ui.screen.smileDetection.SmileDetectionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { SmileDetectionViewModel(get(),get(),get(),androidApplication()) }
}