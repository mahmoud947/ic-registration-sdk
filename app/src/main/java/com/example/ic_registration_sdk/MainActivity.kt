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
import com.example.ic_registration_sdk.ui.theme.IcregistrationsdkTheme
import com.example.icr_sdk.*
import com.example.icr_sdk.module.ICRSdkResult
import com.example.icr_sdk.utils.ICRSDKListener

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val icrsdk = ICRSDK
            .Builder()
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
