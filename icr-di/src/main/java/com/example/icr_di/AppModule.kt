package com.example.icr_di

import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    localModule,
    repositoryModule,
    useCaseModule,
)