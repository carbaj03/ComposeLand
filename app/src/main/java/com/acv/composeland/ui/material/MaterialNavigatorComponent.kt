package com.acv.composeland.ui.material

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.ui.screen.BottomAppBarScreen
import com.acv.composeland.ui.screen.ColorScreen
import com.acv.composeland.ui.screen.MaterialScreen

class MaterialNavigatorComponent(private val navController: NavHostController) : MaterialNavigator {
    override fun goBack() {
        navController.popBackStack()
    }

    override fun goBottomAppBar() {
        navController.navigate(route = BottomAppBarScreen.Main.route)
    }

    override fun goButton() {
        navController.navigate(route = MaterialScreen.Button.route)
    }

    override fun goText() {
        navController.navigate(route = MaterialScreen.Text.route)
    }

    override fun goColor() {
        navController.navigate(route = ColorScreen.Main.route)
    }
}