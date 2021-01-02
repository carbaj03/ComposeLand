package com.acv.composeland.ui.theming.codelab

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.ui.screen.BottomAppBarScreen
import com.acv.composeland.ui.screen.ColorScreen
import com.acv.composeland.ui.screen.MaterialScreen

class ThemingCodelabNavigatorComponent(
    private val navController: NavHostController
) : ThemingCodelabNavigator {

    override fun goBack() {
        navController.popBackStack()
    }
}