package com.acv.composeland.ui.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.button.*

sealed class ButtonScreen(val route: String) {
    object Main : ButtonScreen("button_main")
    object Color : ButtonScreen("button_color")
    object Click : ButtonScreen("button_click")
    object Enabled : ButtonScreen("button_enabled")
    object InteractionState : ButtonScreen("button_interaction_state")
    object Shape : ButtonScreen("button_shape")
    object Border : ButtonScreen("button_border")
}

fun NavGraphBuilder.button(navController: NavHostController) {
    composable(ButtonScreen.Main.route) { ButtonMain(navController = navController) }
    composable(ButtonScreen.Color.route) { ButtonColor(navController = navController) }
    composable(ButtonScreen.Click.route) { ButtonClick(navController = navController) }
    composable(ButtonScreen.Enabled.route) { ButtonEnabled(navController = navController) }
    composable(ButtonScreen.InteractionState.route) { ButtonInteractionState(navController = navController) }
    composable(ButtonScreen.Shape.route) { ButtonShape(navController = navController) }
    composable(ButtonScreen.Border.route) { ButtonBorder(navController = navController) }
}
