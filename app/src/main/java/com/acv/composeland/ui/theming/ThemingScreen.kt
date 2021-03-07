package com.acv.composeland.ui.theming

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.text.TextMain
import com.acv.composeland.ui.theming.codelab.BackdropScaffoldSample
import com.acv.composeland.ui.theming.codelab.ThemingCodelabNavigatorComponent

sealed class ThemingScreen(val route: String) {
    object Guide : ThemingScreen("theming_guide")
    object Codelab : ThemingScreen("theming_codelab")
    object Text : ThemingScreen("theming_text")
//    object Intro : ThemingScreen("theming_intro")
//    object Setup : ThemingScreen("theming_setup")
}

fun NavGraphBuilder.theming(navController: NavHostController) {
    val guide: ThemingNavigatorComponent = ThemingNavigatorComponent(navController = navController)
    val codelab = ThemingCodelabNavigatorComponent(navController = navController)

    composable(ThemingScreen.Guide.route) {
        ThemingGuideScreen(navigator = guide)
    }
    composable(ThemingScreen.Codelab.route) {
        BackdropScaffoldSample(codelab)
//        ThemingCodelab(codelab)
    }
    composable(ThemingScreen.Text.route) {
        TextMain(navController = navController)
    }
//    composable(ThemingScreen.Intro.route) {
//        FirstStep()
//    }
//    composable(ThemingScreen.Setup.route) {
//        FirstStep()
//    }
}
