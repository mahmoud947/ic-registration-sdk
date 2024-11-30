package com.example.icr_data.mapper

import com.example.icr_core.base.Mapper
import com.example.icr_data.datasource.local.entities.ICRUserEntity
import com.example.icr_domain.models.ICRUser

object ICRUserToEntityMapper : Mapper<ICRUser, ICRUserEntity> {
    override fun map(from: ICRUser): ICRUserEntity {
        return ICRUserEntity(
            id = from.id,
            username = from.username,
            phoneNumber = from.phoneNumber,
            email = from.email,
            password = from.password,
        )
    }
}

object EntityToICRUserMapper : Mapper<ICRUserEntity, ICRUser> {
    override fun map(from: ICRUserEntity): ICRUser {
        return ICRUser(
            id = from.id,
            username = from.username,
            phoneNumber = from.phoneNumber,
            email = from.email,
            password = from.password,
        )
    }
}