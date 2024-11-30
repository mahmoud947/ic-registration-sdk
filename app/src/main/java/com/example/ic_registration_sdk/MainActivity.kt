package com.example.ic_registration_sdk

import android.os.Bundle
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val icrsdk = ICRBuilder.Builder().context(this.applicationContext).build()
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
                            icrsdk.insertNewUser(this@MainActivity)
                        }) {
                            Text(text = "Insert User")
                        }
                    }
                }
            }
        }
    }
}
