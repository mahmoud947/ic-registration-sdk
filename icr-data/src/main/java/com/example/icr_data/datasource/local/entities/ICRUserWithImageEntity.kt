package com.example.icr_data.datasource.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ICRUserWithImageEntity(
    @Embedded val user: ICRUserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val image: ICRImageEntity?
)
