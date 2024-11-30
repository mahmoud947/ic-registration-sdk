package com.example.icr_sdk

import android.content.Context
import android.content.Intent
import com.example.icr_data.datasource.local.ICRDatabase

class ICRBuilder private constructor(
    private val icDatabase: ICRDatabase,
) {
    suspend fun insertUser() {
        icDatabase.insertDemoUser()
    }

    fun insertNewUser(context: Context){
        val intent = Intent(context, ICRActivity::class.java)
        context.startActivity(intent)
    }

    class Builder {
        private lateinit var context: Context

        fun context(context: Context): Builder {
            this.context = context
            return this
        }

        fun build() = ICRBuilder(
            icDatabase = ICRDatabase.getInstance(context)
        )
    }

}