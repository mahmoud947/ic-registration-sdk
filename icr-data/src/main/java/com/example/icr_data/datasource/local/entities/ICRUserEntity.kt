package com.example.icr_data.datasource.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "icr_users")
data class ICRUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val phoneNumber: String,
    val email: String,
    val password: String
)
