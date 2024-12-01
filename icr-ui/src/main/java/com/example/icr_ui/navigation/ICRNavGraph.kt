package com.example.icr_ui.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.icr_ui.navigation.screens.Args
import com.example.icr_ui.navigation.screens.ICRScreen
import com.example.icr_ui.screen.registration.RegistrationContract
import com.example.icr_ui.screen.registration.RegistrationScreen
import com.example.icr_ui.screen.registration.RegistrationViewModel
import com.example.icr_ui.screen.smileDetection.SmileDetectionContract
import com.example.icr_ui.screen.smileDetection.SmileDetectionScreen
import com.example.icr_ui.screen.smileDetection.SmileDetectionViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        route = RootGraph.Main,
        startDestination = ICRScreen.Registration.route,
    ) {
        composable(route = ICRScreen.Registration.route) {
            val viewModel: RegistrationViewModel = koinViewModel()
            val state: RegistrationContract.State by viewModel.viewState.collectAsStateWithLifecycle()
            RegistrationScreen(
                uiState = state,
                onEvent = viewModel::setEvent,
                sideEffect = viewModel.effect,
                navController = navController
            )
        }

        composable(
            route = ICRScreen.SmileDetection.route,
            arguments = listOf(navArgument(Args.userId) { type = NavType.LongType })
        ) {
            val viewModel: SmileDetectionViewModel = koinViewModel()
            val state: SmileDetectionContract.State by viewModel.viewState.collectAsStateWithLifecycle()
            val userId = it.arguments?.getLong(Args.userId) ?: 0
            SmileDetectionScreen(
                uiState = state,
                userId = userId,
                onEvent = viewModel::setEvent,
                sideEffect = viewModel.effect,
                navController = navController
            )
        }
    }
}