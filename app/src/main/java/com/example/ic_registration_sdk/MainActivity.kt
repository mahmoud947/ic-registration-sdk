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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.ic_registration_sdk.ui.theme.IcregistrationsdkTheme
import com.example.icr_sdk.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val icrsdk = ICRSDK
            .Builder()
            .context(this.applicationContext).build()
        enableEdgeToEdge()
        setContent {

            val scope = rememberCoroutineScope()
            IcregistrationsdkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Button(onClick = {
//                            scope.launch(Dispatchers.IO) {
//                                icrsdk.insertUser()
//                            }
                            icrsdk.validateNewUser(this@MainActivity, object : ICRSDKTListener {
                                override fun onValidationSuccess() {
                                    TODO("Not yet implemented")
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
