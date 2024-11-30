package com.example.icr_data.datasource.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "icr_users")
data class ICRUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
