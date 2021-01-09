package com.acv.composeland.ui.theming.codelab

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.ui.theming.ThemingScreen

class ThemingCodelabNavigatorComponent(
    private val navController: NavHostController
) : ThemingCodelabNavigator {

    override fun goBack() {
        navController.popBackStack()
    }

//    override fun intro() {
//        navController.navigate(route = ThemingScreen.Intro.route)
//    }

//    override fun setup() {
//      “  navController.navigate(route = ThemingScreen.Setup.route)
//    }
}