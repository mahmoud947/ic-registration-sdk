package com.example.icr_ui.navigation.screens

sealed class ICRScreen(val route: String) {
    data object Registration : ICRScreen("registration")
    data object SmileDetection : ICRScreen("smile_detection")

}