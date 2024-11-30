package com.example.icr_data.repositories

import com.example.icr_core.base.Mapper
import com.example.icr_data.datasource.local.daos.ICRUserDao
import com.example.icr_data.datasource.local.entities.ICRUserEntity
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.repositories.AuthRepository

class AuthRepositoryImpl(
    private val icrUserDao: ICRUserDao,
    private val toEntityMapper: Mapper<ICRUser, ICRUserEntity>,
) : AuthRepository {

    override suspend fun insertUser(user: ICRUser): Long {
        val entity = toEntityMapper.map(user)
        return icrUserDao.insertUser(entity)
    }

}