package com.example.icr_di

import com.example.icr_ui.screen.TestScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TestScreenViewModel(get()) }
}