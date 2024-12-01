package com.example.icr_di

import com.example.icr_data.repositories.AuthRepositoryImpl
import com.example.icr_data.repositories.FaceDetectionRepositoryImpl
import com.example.icr_domain.repositories.AuthRepository
import com.example.icr_domain.repositories.FaceDetectionRepository
import org.koin.dsl.bind
import org.koin.dsl.module


val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<FaceDetectionRepository> { FaceDetectionRepositoryImpl(get()) }
}