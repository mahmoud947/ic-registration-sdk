package com.example.icr_di

import com.example.icr_core.base.Mapper
import com.example.icr_data.datasource.local.entities.ICRUserEntity
import com.example.icr_data.mapper.EntityToICRUserMapper
import com.example.icr_data.mapper.ICRUserToEntityMapper
import com.example.icr_domain.models.ICRUser
import org.koin.dsl.factory
import org.koin.dsl.module

val mapperModule = module {
    factory<Mapper<ICRUser, ICRUserEntity>> { ICRUserToEntityMapper }
    factory<Mapper<ICRUserEntity, ICRUser>> { EntityToICRUserMapper }
}