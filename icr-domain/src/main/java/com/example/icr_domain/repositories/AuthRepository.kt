package com.example.icr_domain.repositories

import com.example.icr_domain.models.ICRUser

interface AuthRepository {
    suspend fun insertUser(user: ICRUser): Long
}