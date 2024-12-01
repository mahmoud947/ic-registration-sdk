package com.example.icr_data.mapper

import com.example.icr_core.base.Mapper
import com.example.icr_data.datasource.local.entities.ICRImageEntity
import com.example.icr_domain.models.ICRImage

object ICRImageToEntityMapper:Mapper<ICRImage, ICRImageEntity>{
    override fun map(from: ICRImage): ICRImageEntity {
        return ICRImageEntity(
            id = from.id,
            uri = from.uri,
            userId = from.userId
        )
    }
}

object EntityToICRImage: Mapper<ICRImageEntity, ICRImage>{
    override fun map(from: ICRImageEntity): ICRImage {
        return ICRImage(
            id = from.id,
            uri = from.uri,
            userId = from.userId
        )
    }
}