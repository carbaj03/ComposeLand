package com.acv.composeland.ui.compose

import androidx.navigation.NavHostController
import com.acv.composeland.ui.animation.AnimationScreen
import com.acv.composeland.ui.screen.MainScreen
import com.acv.composeland.ui.screen.MaterialScreen
import com.acv.composeland.ui.theming.ThemingScreen

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

    override fun goThemingGuide() {
        navController.navigate(ThemingScreen.Guide.route)
    }

    override fun goThemingCodelab() {
        navController.navigate(ThemingScreen.Codelab.route)
    }

    override fun goAnimationGuide() {
        navController.navigate(AnimationScreen.Main.route)
    }
}