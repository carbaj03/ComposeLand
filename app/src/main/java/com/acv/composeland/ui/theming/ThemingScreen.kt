package com.acv.composeland.ui.theming

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.text.TextMain
import com.acv.composeland.ui.theming.codelab.ThemingCodelab
import com.acv.composeland.ui.theming.codelab.ThemingCodelabNavigatorComponent

sealed class ThemingScreen(val route: String) {
    object Guide : ThemingScreen("theming_guide")
    object Codelab : ThemingScreen("theming_codelab")
    object Text : ThemingScreen("theming_text")
}

fun NavGraphBuilder.theming(navController: NavHostController) {
    val guide = ThemingNavigatorComponent(navController = navController)
    val codelab = ThemingCodelabNavigatorComponent(navController = navController)

    composable(ThemingScreen.Guide.route) {
        ThemingGuide(guide)
    }
    composable(ThemingScreen.Codelab.route) {
        ThemingCodelab(codelab)
    }
    composable(ThemingScreen.Text.route) {
        TextMain(navController = navController)
    }
}
