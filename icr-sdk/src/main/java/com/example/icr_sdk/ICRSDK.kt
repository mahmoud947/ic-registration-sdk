package com.example.icr_sdk

import android.content.Context
import android.content.Intent
import com.example.icr_core.enums.Language
import com.example.icr_core.listner.ICRSDKListener
import com.example.icr_core.listner.ICRSDKManager


class ICRSDK private constructor() {

    fun validateNewUser(context: Context,listener:ICRSDKTListener) {

        val intent = Intent(context, ICRActivity::class.java)
        ICRSDKManager.listener = object : ICRSDKListener {
            override fun onValidationSuccess() {
                listener.onValidationSuccess()
            }
            override fun onValidationFailure(exception: Exception) {
                listener.onValidationFailure(exception)
            }
        }
        context.startActivity(intent)
    }

    class Builder {
        private lateinit var context: Context
        private var language: Language? = null
        private var listener: ICRSDKListener? = null

        fun context(context: Context): Builder {
            this.context = context
            return this
        }
        fun setLanguage(language: Language): Builder {
            this.language = language
            return this
        }

        fun build() = ICRSDK()
    }

}