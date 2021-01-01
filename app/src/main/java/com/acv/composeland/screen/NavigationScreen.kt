package com.acv.composeland.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.navigation.NavigationMain

sealed class NavigationScreen(val route: String) {
    object Main : NavigationScreen("navigation_main")
}

fun NavGraphBuilder.navigation(navController: NavHostController) {
    composable(NavigationScreen.Main.route) { NavigationMain(navController = navController) }
}