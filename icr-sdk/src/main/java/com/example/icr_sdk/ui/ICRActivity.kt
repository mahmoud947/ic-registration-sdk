package com.example.icr_sdk.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.icr_ui.navigation.ICRNavHost
import com.example.icr_ui.theme.IcregistrationsdkTheme

class ICRActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IcregistrationsdkTheme {
                val navController = rememberNavController()
                ICRNavHost(navController = navController)
            }
        }
    }
}
