package com.example.icr_di

import com.example.icr_data.repositories.AuthRepositoryImpl
import com.example.icr_domain.repositories.AuthRepository
import org.koin.dsl.bind
import org.koin.dsl.module


val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}