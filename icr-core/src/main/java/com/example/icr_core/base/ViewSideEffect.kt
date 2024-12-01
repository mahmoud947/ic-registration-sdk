package com.example.icr_core.base

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

interface ViewSideEffect

data class ShowMessage(
    @RawRes val icon: Int? = null,
    @StringRes val title: Int? = null,
    @StringRes val message: Int? = null,
    val positiveAction: () -> Unit = {},
) : ViewSideEffect

data class ShowToast(@StringRes val message: Int) : ViewSideEffect


@Composable
fun Flow<ViewSideEffect>.OnEffect(action: (effect: ViewSideEffect) -> Unit) {
    LaunchedEffect(Unit) {
        onEach { effect ->
            action(effect)
        }.collect()
    }
}