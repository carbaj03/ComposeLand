package com.acv.composeland.ui.compose

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.ui.screen.MainScreen
import com.acv.composeland.ui.screen.MaterialScreen

class MainNavigatorComponent(
    private val navController: NavHostController
) : MainNavigator {
    override fun goBack() {
        navController.popBackStack()
    }

    override fun goCore() {
        navController.navigate(MainScreen.Core.route)
    }

    override fun goMain() {
        navController.navigate(MaterialScreen.Main.route)
    }
}