package com.example.icr_ui.screen.registration

import com.example.icr_core.base.ViewEvent
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.base.ViewState


class RegistrationContract {
    sealed class Event : ViewEvent {
        data class OnUsernameChange(val username: String) : Event()
        data class OnPhoneNumberChange(val phoneNumber: String) : Event()
        data class OnEmailChange(val email: String) : Event()
        data class OnPasswordChange(val password: String) : Event()
        data class OnConfirmPasswordChange(val confirmPassword: String) : Event()
        data object OnNextClick : Event()
        data object OnCancel : Event()
    }

    sealed class SideEffect : ViewSideEffect {
        data class NavigateToNextScreen(val userId: Long) : SideEffect()
        data object Exit: SideEffect()
        data object Cancel: SideEffect()
    }

    data class State(
        override val loading: Boolean = false,
        val username: String = "",
        val usernameResErrorId: Int? = null,

        val phoneNumber: String = "",
        val phoneNumberResErrorId: Int? = null,

        val email: String = "",
        val emailResErrorId: Int? = null,

        val password: String = "",
        val passwordResErrorId: Int? = null,

        val confirmPassword: String = "",
        val confirmPasswordResErrorId: Int? = null,
    ) : ViewState
}