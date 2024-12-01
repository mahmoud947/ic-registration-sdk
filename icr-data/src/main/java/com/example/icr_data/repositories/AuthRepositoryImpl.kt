package com.example.icr_data.repositories

import com.example.icr_core.base.Mapper
import com.example.icr_data.datasource.local.daos.ICRUserDao
import com.example.icr_data.datasource.local.entities.ICRUserEntity
import com.example.icr_data.mapper.ICRUserToEntityMapper
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val icrUserDao: ICRUserDao,
) : AuthRepository {

    override suspend fun insertUser(user: ICRUser): Long {
        val entity: ICRUserEntity = ICRUserToEntityMapper.map(user)
        return icrUserDao.insertUser(entity)
    }

    override suspend fun deleteUserId(id: Int) {
        icrUserDao.deleteUserById(id)
    }

}