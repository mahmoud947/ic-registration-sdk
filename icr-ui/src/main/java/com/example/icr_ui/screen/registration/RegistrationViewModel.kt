package com.example.icr_ui.screen.registration

import com.example.icr_core.base.ICRViewModel
import com.example.icr_core.base.Resource
import com.example.icr_core.base.ShowToast
import com.example.icr_domain.models.ICRUser
import com.example.icr_domain.usecases.auth.RegisterNewUserUseCase
import com.example.icr_domain.usecases.validation.FormValidationUseCase
import com.example.icr_domain.usecases.validation.ValidateConfirmPasswordUseCaseIN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import com.example.icr_ui.R

private const val TAG = "TestScreenViewModel"

class RegistrationViewModel(
    private val insertNewUserUseCase: RegisterNewUserUseCase,
    private val formValidationUseCase: FormValidationUseCase,
) : ICRViewModel<RegistrationContract.Event, RegistrationContract.State>() {
    override fun setInitialState(): RegistrationContract.State = RegistrationContract.State()

    override fun handleEvents(event: RegistrationContract.Event) {
        when (event) {
            RegistrationContract.Event.OnNextClick -> insertNewUser()
            is RegistrationContract.Event.OnConfirmPasswordChange -> onConfirmPasswordChange(event.confirmPassword)
            is RegistrationContract.Event.OnEmailChange -> onEmailChange(event.email)
            is RegistrationContract.Event.OnPasswordChange -> onPasswordChange(event.password)
            is RegistrationContract.Event.OnPhoneNumberChange -> onPhoneNumberChange(event.phoneNumber)
            is RegistrationContract.Event.OnUsernameChange -> onUsernameChange(event.username)
        }
    }


    private fun insertNewUser() = launchCoroutine(Dispatchers.IO) {

        if (isValidForm()) {
            insertNewUserUseCase(
                input = ICRUser(
                    phoneNumber = viewState.value.phoneNumber,
                    email = viewState.value.email,
                    password = viewState.value.password,
                    username = viewState.value.username
                )
            ).flowOn(Dispatchers.IO)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            setState { copy(loading = false) }
                        }

                        Resource.Loading -> {
                            setState { copy(loading = true) }
                        }

                        is Resource.Success -> {
                            setState { copy(loading = false) }
                            setEffect {
                                ShowToast(
                                    message = R.string.successfully,
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun isValidForm(): Boolean {
        val usernameRes = formValidationUseCase.validateTextUseCase(viewState.value.username)
        val phoneNumberRes = formValidationUseCase.validatePhoneNumber(viewState.value.phoneNumber)
        val emailRes = formValidationUseCase.validateEmail(viewState.value.email)
        val passwordRes = formValidationUseCase.validatePassword(viewState.value.password)
        val confirmPasswordRes =
            formValidationUseCase.validateConfirmPassword(
                ValidateConfirmPasswordUseCaseIN(
                    password = viewState.value.password,
                    confirmPassword = viewState.value.confirmPassword
                )
            )
        val hasError = listOf(
            usernameRes,
            phoneNumberRes,
            emailRes,
            passwordRes,
            confirmPasswordRes
        ).any { validator ->
            !validator.success
        }
        if (hasError) {
            setState {
                copy(
                    usernameResErrorId = usernameRes.messageResId,
                    phoneNumberResErrorId = phoneNumberRes.messageResId,
                    emailResErrorId = emailRes.messageResId,
                    passwordResErrorId = passwordRes.messageResId,
                    confirmPasswordResErrorId = confirmPasswordRes.messageResId,
                )
            }
            return false
        } else {
            setState {
                copy(
                    usernameResErrorId = null,
                    phoneNumberResErrorId = null,
                    emailResErrorId = null,
                    passwordResErrorId = null,
                    confirmPasswordResErrorId = null,
                )
            }
        }
        return true
    }

    private fun onUsernameChange(username: String) {
        setState { copy(username = username) }
    }

    private fun onPhoneNumberChange(phoneNumber: String) {
        setState { copy(phoneNumber = phoneNumber) }
    }

    private fun onPasswordChange(password: String) {
        setState { copy(password = password) }
    }

    private fun onEmailChange(email: String) {
        setState { copy(email = email) }
    }

    private fun onConfirmPasswordChange(password: String) {
        setState { copy(confirmPassword = password) }
    }

}