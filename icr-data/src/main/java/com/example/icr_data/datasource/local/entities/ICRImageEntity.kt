package com.example.icr_data.datasource.local.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "icr_image",
    foreignKeys = [ForeignKey(
        entity = ICRUserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [androidx.room.Index("userId", unique = true)],
)
data class ICRImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val uri: Uri,
    val userId: Int
)
