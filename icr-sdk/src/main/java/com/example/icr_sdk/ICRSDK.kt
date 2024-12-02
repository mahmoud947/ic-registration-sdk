package com.example.icr_sdk

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
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
        private var isDarkMode: Boolean = false
        private var primary: Color? = null
        private var secondary: Color? = null
        private var tertiary: Color? = null
        private var background: Color? = null
        private var lightPrimary: Color? = null
        private var lightSecondary: Color? = null
        private var lightTertiary: Color? = null
        private var lightBackground: Color? = null

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

        fun setDarkMode(isDarkMode: Boolean): Builder {
            this.isDarkMode = isDarkMode
            ICRSDKManager.isDarkMode = isDarkMode
            return this
        }

        fun setDarkThemeColors(
            primary: Color? = null,
            secondary: Color? = null,
            tertiary: Color? = null,
            background: Color? = null
        ): Builder {
            this.primary = primary
            this.secondary = secondary
            this.tertiary = tertiary
            this.background = background
            ICRSDKManager.primary = primary
            ICRSDKManager.secondary = secondary
            ICRSDKManager.tertiary = tertiary
            ICRSDKManager.background = background
            return this
        }

        fun setLightThemeColors(
            lightPrimary: Color? = null,
            lightSecondary: Color? = null,
            lightTertiary: Color? = null,
            lightBackground: Color? = null
        ): Builder {
            this.lightPrimary = lightPrimary
            this.lightSecondary = lightSecondary
            this.lightTertiary = lightTertiary
            this.lightBackground = lightBackground
            ICRSDKManager.lightPrimary = lightPrimary
            ICRSDKManager.lightSecondary = lightSecondary
            ICRSDKManager.lightTertiary = lightTertiary
            ICRSDKManager.lightBackground = lightBackground
            return this
        }

        fun build(): ICRSDK {
            // You can add validations or default settings here if needed
            return ICRSDK()
        }
    }
}