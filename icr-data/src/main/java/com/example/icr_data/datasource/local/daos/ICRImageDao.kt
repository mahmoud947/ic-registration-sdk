package com.example.icr_data.datasource.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.icr_data.datasource.local.entities.ICRImageEntity

@Dao
interface ICRImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: ICRImageEntity): Long

    @Query("SELECT * FROM icr_image WHERE userId = :userId")
    suspend fun getImageByUserId(userId: Int): ICRImageEntity?

    @Query("DELETE FROM icr_image WHERE userId = :userId")
    suspend fun deleteImagesByUserId(userId: Int)

    @Query("DELETE FROM icr_image WHERE id = :imageId")
    suspend fun deleteImageById(imageId: Int)

}