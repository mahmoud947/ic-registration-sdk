package com.example.icr_sdk.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.example.icr_core.enums.Language
import com.example.icr_core.listner.ICRSDKManager
import com.example.icr_core.utils.setAppLocale
import com.example.icr_sdk.utils.ICRLanguage
import com.example.icr_ui.navigation.ICRNavHost
import com.example.icr_ui.theme.IcregistrationsdkTheme

class ICRActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent() {
            IcregistrationsdkTheme {
                val isRtl = if (ICRSDKManager.language == null) false else ICRSDKManager.language == Language.ARABIC
                val layoutDirection = if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
                setAppLocale(ICRSDKManager.language?.language ?: "en")
                CompositionLocalProvider(LocalLayoutDirection provides layoutDirection){
                    val navController = rememberNavController()
                    ICRNavHost(navController = navController)
                }

            }
        }
    }
}
