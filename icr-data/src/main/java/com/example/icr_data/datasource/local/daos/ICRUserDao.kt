package com.example.icr_data.datasource.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.icr_data.datasource.local.entities.ICRUserEntity
import com.example.icr_data.datasource.local.entities.ICRUserWithImageEntity

@Dao
interface ICRUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: ICRUserEntity): Long

    @Query("SELECT * FROM icr_users WHERE email = :email")
    suspend fun getUserByEmail(email: String): ICRUserEntity?

    @Query("SELECT * FROM icr_users WHERE id = :id")
    suspend fun getUserById(id: Int): ICRUserEntity?

    @Query("SELECT * FROM icr_users")
    suspend fun getAllUsers(): List<ICRUserEntity>

    @Query("DELETE FROM icr_users WHERE id = :id")
    suspend fun deleteUserById(id: Int)

    @Transaction
    @Query("SELECT * FROM icr_users WHERE id = :userId")
    suspend fun getUserWithImageByUserId(userId: Int): ICRUserWithImageEntity?
}