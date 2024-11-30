package com.example.icr_ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun ICRNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = RootGraph.Root,
        startDestination = RootGraph.Main,
    ) {
        mainNavGraph(navController = navController)
    }
}