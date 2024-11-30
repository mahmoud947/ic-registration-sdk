package com.example.icr_sdk

import android.content.Context
import android.content.Intent
import com.example.icr_di.appModules
import com.example.icr_sdk.ICRApplicationClass
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class ICRBuilder private constructor() {

    fun insertNewUser(context: Context) {
        val intent = Intent(context, ICRActivity::class.java)
        context.startActivity(intent)
    }

    class Builder {
        private lateinit var context: Context

        fun context(context: Context): Builder {
            this.context = context
            return this
        }

        fun build() = ICRBuilder()
    }

}