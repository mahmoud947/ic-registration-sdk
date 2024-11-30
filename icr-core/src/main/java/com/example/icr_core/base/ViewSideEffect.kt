package com.example.icr_core.base

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

interface ViewSideEffect

data class ShowMessage(@StringRes val message: Int) : ViewSideEffect

data class ShowError(@StringRes val message: Int) : ViewSideEffect

data class ShowToast(@StringRes val message: Int) : ViewSideEffect

data class ShowSnackBar(@StringRes val message: Int) : ViewSideEffect

@Composable
fun Flow<ViewSideEffect>.OnEffect(action: (effect: ViewSideEffect) -> Unit) {
    LaunchedEffect(Unit) {
        onEach { effect ->
            action(effect)
        }.collect()
    }
}