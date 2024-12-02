package com.example.ic_registration_sdk

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
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
            var result by remember {
                mutableStateOf<ICRSdkResult?>(null)
            }
            IcregistrationsdkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        result?.let {
                            UserDetailsScreen(icrSdkResult = it, modifier = Modifier)
                        }


                        Button(onClick = {
                            icrsdk.validateNewUser(this@MainActivity, object : ICRSDKListener {
                                override fun onValidationSuccess(user: ICRSdkResult) {
                                    result = user
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
                            Text(text = "Vilify new user")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun UserDetailsScreen(icrSdkResult: ICRSdkResult,modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            if (icrSdkResult.imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = icrSdkResult.imageUri),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.secondary
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = icrSdkResult.userName.take(1).uppercase(),
                        fontSize = 36.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            UserDetailsItem(label = "User ID", value = icrSdkResult.userId.toString())
            UserDetailsItem(label = "Username", value = icrSdkResult.userName)
            UserDetailsItem(label = "Email", value = icrSdkResult.email)
            UserDetailsItem(label = "Phone Number", value = icrSdkResult.phoneNumber)
            UserDetailsItem(label = "Password", value = icrSdkResult.password)
        }

}

@Composable
fun UserDetailsItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}