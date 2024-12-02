package com.example.icr_sdk

import android.content.Context
import android.content.Intent
import com.example.icr_core.base.ICRResult
import com.example.icr_core.enums.Language
import com.example.icr_core.listner.ICRSDKCoreListener
import com.example.icr_core.listner.ICRSDKManager
import com.example.icr_sdk.module.ICRSdkResult
import com.example.icr_sdk.ui.ICRActivity
import com.example.icr_sdk.utils.ICRLanguage
import com.example.icr_sdk.utils.ICRSDKListener


class ICRSDK private constructor() {

    fun validateNewUser(context: Context, listener: ICRSDKListener) {
        val intent = Intent(context, ICRActivity::class.java)
        ICRSDKManager.listener = object : ICRSDKCoreListener {
            override fun onValidationSuccess(icrResult: ICRResult) {
                val result = ICRSdkResult(
                    userId = icrResult.userId.toLong(),
                    email = icrResult.userEmail,
                    phoneNumber = icrResult.userPhoneNumber,
                    password = icrResult.userPassword,
                    userName = icrResult.userName,
                    imageUri = icrResult.imageUrl
                )
                listener.onValidationSuccess(result)
            }

            override fun onValidationFailure(exception: Exception) {
                listener.onValidationFailure(exception)
            }

            override fun onCancelByUser() {
                listener.onCancelByTheUser()
            }
        }
        context.startActivity(intent)
    }

    class Builder {
        private lateinit var context: Context
        private var language: ICRLanguage? = null

        fun context(context: Context): Builder {
            this.context = context
            return this
        }

        fun setLanguage(language: ICRLanguage): Builder {
            this.language = language
            ICRSDKManager.language = when (language) {
                ICRLanguage.ENGLISH -> Language.ENGLISH
                ICRLanguage.ARABIC -> Language.ARABIC
            }
            return this
        }

        fun build() = ICRSDK()
    }

}