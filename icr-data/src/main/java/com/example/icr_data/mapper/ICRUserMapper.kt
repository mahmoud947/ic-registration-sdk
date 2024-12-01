package com.example.icr_data.mapper

import com.example.icr_core.base.Mapper
import com.example.icr_data.datasource.local.entities.ICRUserEntity
import com.example.icr_data.datasource.local.entities.ICRUserWithImageEntity
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.models.ICRUserWithImage


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

object UserWithImageEntityToDomainMapper : Mapper<ICRUserWithImageEntity, ICRUserWithImage> {
    override fun map(from: ICRUserWithImageEntity): ICRUserWithImage {
        return ICRUserWithImage(
            userId = from.user.id ?: 0,
            username = from.user.username,
            userEmail = from.user.email,
            imageId = from.image?.id,
            imageUrl = from.image?.uri,
            userPhoneNumber = from.user.phoneNumber,
            userPassword = from.user.password,
        )
    }
}