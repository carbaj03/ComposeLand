package com.acv.composeland.ui.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.navigation.NavigationMain

sealed class NavigationScreen(val route: String) {
    object Main : NavigationScreen("navigation_main")
}

fun NavGraphBuilder.navigation(navController: NavHostController) {
    composable(NavigationScreen.Main.route) { NavigationMain(navController = navController) }
}