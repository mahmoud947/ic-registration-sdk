package com.example.icr_domain.repositories

import com.example.icr_domain.models.ICRImage
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.models.ICRUserWithImage

interface AuthRepository {
    suspend fun insertUser(user: ICRUser): Long
    suspend fun deleteUserId(id: Int)
    suspend fun insertImage(image: ICRImage): Long
    suspend fun deleteImagesByUserId(userId: Int)
    suspend fun getUserWithImage(userId: Int): ICRUserWithImage?
}