package com.acv.composeland.ui.theming.codelab

import androidx.navigation.NavHostController

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