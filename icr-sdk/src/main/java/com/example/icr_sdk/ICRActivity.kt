package com.example.icr_sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.icr_sdk.ui.theme.IcregistrationsdkTheme

class ICRActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IcregistrationsdkTheme {

            }
        }
    }
}
