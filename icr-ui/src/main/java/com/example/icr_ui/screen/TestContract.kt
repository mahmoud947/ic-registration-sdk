package com.example.icr_ui.screen

import com.example.icr_core.base.ViewEvent
import com.example.icr_core.base.ViewSideEffect
import com.example.icr_core.base.ViewState


class TestContract {
    sealed class Event : ViewEvent {

        data object InsertUser : Event()
    }

    data class State(
        override val loading: Boolean = false,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object Error : Effect()
    }
}