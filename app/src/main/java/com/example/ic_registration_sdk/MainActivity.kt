package com.example.ic_registration_sdk

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ic_registration_sdk.ui.theme.IcregistrationsdkTheme
import com.example.icr_sdk.*
import com.example.icr_sdk.module.ICRSdkResult
import com.example.icr_sdk.utils.ICRLanguage
import com.example.icr_sdk.utils.ICRSDKListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val icrsdk = ICRSDK
            .Builder()
            .setLanguage(ICRLanguage.ARABIC)
            .setDarkMode(false)
            .setLightThemeColors(
                lightPrimary = Color.Red,
                lightSecondary = Color.Green,
                lightTertiary = Color.DarkGray,
                lightBackground = Color.White
            )
            .setAutoCapture(true)
            .context(this.applicationContext).build()
        enableEdgeToEdge()
        setContent {
            IcregistrationsdkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Button(onClick = {
                            icrsdk.validateNewUser(this@MainActivity, object : ICRSDKListener {
                                override fun onValidationSuccess(user: ICRSdkResult) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        user.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                override fun onValidationFailure(exception: Exception) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        exception.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                override fun onCancelByTheUser() {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Sdk onCancelByTheUser",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            })
                        }) {
                            Text(text = "Insert User")
                        }
                    }
                }
            }
        }
    }
}
