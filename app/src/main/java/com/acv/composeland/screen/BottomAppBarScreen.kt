package com.acv.composeland.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.appbar.bottom.*

sealed class BottomAppBarScreen(val route: String) {
    object Main : BottomAppBarScreen("bottomappbar_main")
    object Background : BottomAppBarScreen("bottomappbar_background")
    object ContentColor : BottomAppBarScreen("bottomappbar_content_color")
    object CutoutShape : BottomAppBarScreen("bottomappbar_cutout_shape")
    object Elevation : BottomAppBarScreen("bottomappbar_elevation")
}

fun NavGraphBuilder.bottomAppBar(navController: NavHostController) {
    composable(BottomAppBarScreen.Main.route) { BottomAppBarMain(navController = navController) }
    composable(BottomAppBarScreen.Background.route) { BottomAppBarBackground(navController = navController) }
    composable(BottomAppBarScreen.ContentColor.route) { BottomAppBarContentColor(navController = navController) }
    composable(BottomAppBarScreen.CutoutShape.route) { BottomAppBarCutoutShape(navController = navController) }
    composable(BottomAppBarScreen.Elevation.route) { BottomAppBarElevation(navController = navController) }
}