package com.acv.composeland.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.material.color.ColorMain

sealed class ColorScreen(val route: String) {
    object Main : ColorScreen("color_main")
}

fun NavGraphBuilder.color(navController: NavHostController) {
    composable(ColorScreen.Main.route) { ColorMain(navController = navController) }
}