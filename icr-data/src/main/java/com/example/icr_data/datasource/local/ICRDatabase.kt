package com.example.icr_data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.icr_data.datasource.local.converter.UriConverter
import com.example.icr_data.datasource.local.daos.ICRImageDao
import com.example.icr_data.datasource.local.daos.ICRUserDao
import com.example.icr_data.datasource.local.entities.ICRImageEntity
import com.example.icr_data.datasource.local.entities.ICRUserEntity

@Database(
    entities = [ICRUserEntity::class, ICRImageEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UriConverter::class)
abstract class ICRDatabase : RoomDatabase() {
    abstract val userDao: ICRUserDao
    abstract val imageDao: ICRImageDao


    companion object {
        private const val DATABASE_NAME = "icr_database"
        private lateinit var INSTANCE: ICRDatabase
        fun getInstance(context: Context): ICRDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ICRDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }


}