package com.example.icr_ui.utils

import com.example.icr_core.base.ICRResult
import com.example.icr_domain.models.ICRUserWithImage

 fun ICRUserWithImage.toResult(): ICRResult {
    return ICRResult(
        userId = this.userId,
        userName = this.username,
        userEmail = this.userEmail,
        imageId = this.imageId,
        imageUrl = this.imageUrl,
        userPassword = this.userPassword,
        userPhoneNumber = this.userPhoneNumber
    )
}