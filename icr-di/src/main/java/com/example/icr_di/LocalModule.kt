package com.example.icr_di

import android.content.Context
import com.example.icr_data.datasource.local.ICRDatabase
import com.example.icr_data.datasource.local.daos.ICRUserDao
import org.koin.dsl.module

val localModule = module {
    single { provideDatabase(get()) }
    single { provideUserDao(get()) }
}

fun provideDatabase(context: Context): ICRDatabase {
    return ICRDatabase.getInstance(context)
}

fun provideUserDao(database: ICRDatabase): ICRUserDao {
    return database.userDao
}