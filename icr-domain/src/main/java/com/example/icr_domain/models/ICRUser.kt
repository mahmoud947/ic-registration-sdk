package com.example.icr_domain.models

data class ICRUser(
    val id: Int? = null,
    val username: String,
    val phoneNumber: String,
    val email: String,
    val password: String
)
