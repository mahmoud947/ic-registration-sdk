package com.example.icr_ui.navigation.screens

sealed class ICRScreen(val route: String) {
    data object Registration : ICRScreen("registration")
    data object SmileDetection : ICRScreen("smile_detection/{userId}"){
        fun createRoute(userId: Long) = "smile_detection/$userId"
    }
}

object Args{
    val userId = "userId"
}