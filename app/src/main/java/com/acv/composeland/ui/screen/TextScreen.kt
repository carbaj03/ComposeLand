package com.acv.composeland.ui.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.text.*

sealed class TextScreen(val route: String) {
    object Main : TextScreen("text_main")
    object Color : TextScreen("text_color")
    object FontSize : TextScreen("text_font_size")
    object FontStyle : TextScreen("text_font_style")
    object FontFamily : TextScreen("text_font_family")
    object FontWeight : TextScreen("text_font_weight")
}

fun NavGraphBuilder.text(navController: NavHostController) {
    composable(TextScreen.Main.route) { TextMain(navController = navController) }
    composable(TextScreen.Color.route) { TextColor(navController = navController) }
    composable(TextScreen.FontSize.route) { TextFontSize(navController = navController) }
    composable(TextScreen.FontFamily.route) { TextFontFamily(navController = navController) }
    composable(TextScreen.FontStyle.route) { TextFontStyle(navController = navController) }
    composable(TextScreen.FontWeight.route) { TextFontWeight(navController = navController) }
}