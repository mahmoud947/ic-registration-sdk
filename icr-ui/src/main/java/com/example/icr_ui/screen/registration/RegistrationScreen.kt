package com.example.icr_ui.screen.registration

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.icr_core.base.OnEffect
import com.example.icr_core.base.ShowMessage
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.listner.ICRSDKManager
import com.example.icr_core.utils.Margin
import com.example.icr_domain.R
import com.example.icr_ui.components.ICRBottomSheet
import com.example.icr_ui.components.ICRFlatButton
import com.example.icr_ui.components.ICRLottiAnimation
import com.example.icr_ui.components.ICROutlinedTextField
import com.example.icr_ui.components.ICRText
import com.example.icr_ui.navigation.screens.ICRScreen
import com.example.icr_ui.theme.IcregistrationsdkTheme
import com.example.icr_ui.theme.extraLarge
import com.example.icr_ui.theme.extraSmall
import com.example.icr_ui.theme.large
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    uiState: RegistrationContract.State = RegistrationContract.State(),
    onEvent: (RegistrationContract.Event) -> Unit = {},
    sideEffect: Flow<ViewSideEffect> = emptyFlow(),
    navController: NavController = rememberNavController()
) {
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var bottomSheet by remember { mutableStateOf(ShowMessage()) }

    val bottomSheetSate = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var isDialogVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    sideEffect.OnEffect { effect ->
        when (effect) {
            is ShowMessage -> {
                bottomSheet = effect
                scope.launch {
                    bottomSheetSate.show()
                    isBottomSheetOpen = true
                }
            }

            is RegistrationContract.SideEffect.NavigateToNextScreen->{
                navController.navigate(ICRScreen.SmileDetection.createRoute(effect.userId)){
                    popUpTo(ICRScreen.Registration.route){
                        inclusive = true
                    }
                }
            }

            is RegistrationContract.SideEffect.Exit -> {
                (context as? ComponentActivity)?.finish()
            }
            is RegistrationContract.SideEffect.Cancel -> {
                ICRSDKManager.listener?.onCancelByUser()
                (context as? ComponentActivity)?.finish()
            }
        }
    }
    BackHandler {
        isDialogVisible = true
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
                text = stringResource(R.string.registration),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            Margin(large)
            ICRText(
                text = stringResource(R.string.enter_details_to_setup),
                style = MaterialTheme.typography.bodyMedium
            )
            Margin(extraLarge)
            ICROutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                value = uiState.username,
                onValueChange = { onEvent(RegistrationContract.Event.OnUsernameChange(it)) },
                placeholder = stringResource(R.string.username),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                value = uiState.phoneNumber,
                onValueChange = { onEvent(RegistrationContract.Event.OnPhoneNumberChange(it)) },
                placeholder = stringResource(R.string.phone_number),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                value = uiState.email,
                onValueChange = { onEvent(RegistrationContract.Event.OnEmailChange(it)) },
                placeholder = stringResource(R.string.email),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation =  PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                onValueChange = { onEvent(RegistrationContract.Event.OnPasswordChange(it)) },
                placeholder = stringResource(R.string.password),
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation =  PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                value = uiState.confirmPassword,
                onValueChange = { onEvent(RegistrationContract.Event.OnConfirmPasswordChange(it)) },
                placeholder = stringResource(R.string.confirm_password),
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
                text = stringResource(R.string.next),
                isLoading = uiState.loading,
                onClick = { onEvent(RegistrationContract.Event.OnNextClick) }
            )
        }

        if (isBottomSheetOpen) {
            ICRBottomSheet(
                sheetState = bottomSheetSate,
                modifier = Modifier.fillMaxWidth(),
                positiveActionLabel = stringResource(R.string.ok),
                title = stringResource(bottomSheet.title ?: R.string.ok),
                subTitle = stringResource(bottomSheet.message ?: R.string.ok),
                onDismiss = {
                    isBottomSheetOpen = false
                    scope.launch {
                        bottomSheetSate.hide()
                    }
                },
                onPositive = {
                    isBottomSheetOpen = false
                    scope.launch {
                        bottomSheetSate.hide()
                    }
                    bottomSheet.positiveAction()
                },
                iconContent = {
                    ICRLottiAnimation(
                        modifier = Modifier.size(100.dp),
                        rowLottie = bottomSheet.icon ?: R.raw.warning_animation
                    )
                }
            )
        }
    }

    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            title = { ICRText(text = stringResource(R.string.confirm_exit_title)) },
            text = { ICRText(text = stringResource(R.string.confirm_exit_message)) },
            confirmButton = {
                ICRFlatButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.yes),
                    onClick = {
                        isDialogVisible = false
                        onEvent(RegistrationContract.Event.OnCancel)
                    })
            },
            dismissButton = {
                ICRFlatButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { isDialogVisible = false },
                    text = stringResource(R.string.no)
                )
            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_8")
@Composable
private fun RegistrationScreenPreview() {
    IcregistrationsdkTheme {
        RegistrationScreen()
    }
}