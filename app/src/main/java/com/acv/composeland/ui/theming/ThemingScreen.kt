package com.acv.composeland.ui.theming

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.text.TextMain

sealed class ThemingScreen(val route: String) {
    object Main : ThemingScreen("theming_main")
    object Text : ThemingScreen("theming_text")
}

fun NavGraphBuilder.theming(navController: NavHostController) {
    val theming = ThemingNavigatorComponent(navController = navController)

    composable(ThemingScreen.Main.route) {
        ThemingMain(theming)
    }
    composable(ThemingScreen.Text.route) {
        TextMain(navController = navController)
    }
}
