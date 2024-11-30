package com.example.icr_ui.screen.registration

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.icr_core.base.OnEffect
import com.example.icr_core.base.ShowToast
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.utils.Margin
import com.example.icr_ui.components.ICRFlatButton
import com.example.icr_ui.components.ICROutlinedTextField
import com.example.icr_ui.components.ICRText
import com.example.icr_ui.theme.IcregistrationsdkTheme
import com.example.icr_ui.theme.extraLarge
import com.example.icr_ui.theme.extraSmall
import com.example.icr_ui.theme.large
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    uiState: RegistrationContract.State = RegistrationContract.State(),
    onEvent: (RegistrationContract.Event) -> Unit = {},
    sideEffect: Flow<ViewSideEffect> = emptyFlow(),
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    sideEffect.OnEffect { effect ->
        when (effect) {
            is ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
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
                modifier = Modifier.fillMaxWidth(),
                value = uiState.username,
                onValueChange = { onEvent(RegistrationContract.Event.OnUsernameChange(it)) },
                placeholder = "Username",
                isError = uiState.usernameResErrorId != null,
                supportingText = uiState.usernameResErrorId?.let { resMessageId ->
                    {
                        ICRText(
                            text = stringResource(
                                id = resMessageId
                            )
                        )
                    }
                }
            )
            Margin(extraSmall)
            ICROutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.phoneNumber,
                onValueChange = { onEvent(RegistrationContract.Event.OnPhoneNumberChange(it)) },
                placeholder = "Phone Number",
                isError = uiState.phoneNumberResErrorId != null,
                supportingText = uiState.phoneNumberResErrorId?.let { resMessageId ->
                    {
                        ICRText(
                            text = stringResource(
                                id = resMessageId
                            )
                        )
                    }
                }
            )

            Margin(extraSmall)
            ICROutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.email,
                onValueChange = { onEvent(RegistrationContract.Event.OnEmailChange(it)) },
                placeholder = "Email",
                isError = uiState.emailResErrorId != null,
                supportingText = uiState.emailResErrorId?.let { resMessageId ->
                    {
                        ICRText(
                            text = stringResource(
                                id = resMessageId
                            )
                        )
                    }
                }
            )

            Margin(extraSmall)
            ICROutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                onValueChange = { onEvent(RegistrationContract.Event.OnPasswordChange(it)) },
                placeholder = "Password",
                isError = uiState.passwordResErrorId != null,
                supportingText = uiState.passwordResErrorId?.let { resMessageId ->
                    {
                        ICRText(
                            text = stringResource(
                                id = resMessageId
                            )
                        )
                    }
                }
            )

            Margin(extraSmall)
            ICROutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.confirmPassword,
                onValueChange = { onEvent(RegistrationContract.Event.OnConfirmPasswordChange(it)) },
                placeholder = "Confirm Password",
                isError = uiState.confirmPasswordResErrorId != null,
                supportingText = uiState.confirmPasswordResErrorId?.let { resMessageId ->
                    {
                        ICRText(
                            text = stringResource(
                                id = resMessageId
                            )
                        )
                    }
                }

            )
            Margin(extraSmall)
            ICRFlatButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Next",
                isLoading = uiState.loading,
                onClick = { onEvent(RegistrationContract.Event.OnNextClick) }
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