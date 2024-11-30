package com.example.icr_ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.icr_core.utils.Margin
import com.example.icr_ui.components.ICRFlatButton
import com.example.icr_ui.components.ICROutlinedTextField
import com.example.icr_ui.components.ICRText
import com.example.icr_ui.theme.IcregistrationsdkTheme
import com.example.icr_ui.theme.extraLarge
import com.example.icr_ui.theme.extraSmall
import com.example.icr_ui.theme.large


@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(innerPadding)
                .fillMaxSize()
                .padding(top = 48.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            ICRText(
                text = "Registration",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            Margin(large)
            ICRText(
                text = "Enter your details to set up your account.",
                style = MaterialTheme.typography.bodyMedium
            )
            Margin(extraLarge)
            ICROutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Username",
                modifier = Modifier.fillMaxWidth()
            )
            Margin(extraSmall)
            ICROutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Phone Number",
                modifier = Modifier.fillMaxWidth()
            )

            Margin(extraSmall)
            ICROutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Email",
                modifier = Modifier.fillMaxWidth()
            )

            Margin(extraSmall)
            ICROutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Password",
                modifier = Modifier.fillMaxWidth()
            )

            Margin(extraSmall)
            ICROutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = "Confirm Password",
                modifier = Modifier.fillMaxWidth()
            )
            Margin(extraSmall)
            ICRFlatButton(
                text = "Next",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_8")
@Composable
private fun RegistrationScreenPreview() {
    IcregistrationsdkTheme {
        RegistrationScreen()
    }
}