package com.acv.composeland.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.appbar.bottom.BottomAppBarMain
import com.acv.composeland.button.ButtonMain
import com.acv.composeland.chip.ChipMain
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.material.color.ColorMain
import com.acv.composeland.text.TextMain

sealed class MaterialScreen(val route: String) {
    object Main : MaterialScreen("material_main")
    object Text : MaterialScreen("material_text")
    object Color : MaterialScreen("material_color")
    object Button : MaterialScreen("material_button")
    object Chip : MaterialScreen("material_chip")
    object BottomAppBar : MaterialScreen("material_bottomappbar")
}

fun NavGraphBuilder.material(navController: NavHostController) {
    composable(MaterialScreen.Main.route) { MaterialMain(navController = navController) }
    composable(MaterialScreen.Text.route) { TextMain(navController = navController) }
    composable(MaterialScreen.Color.route) { ColorMain(navController = navController) }
    composable(MaterialScreen.Button.route) { ButtonMain(navController = navController) }
    composable(MaterialScreen.Chip.route) { ChipMain(navController = navController) }
    composable(MaterialScreen.BottomAppBar.route) { BottomAppBarMain(navController = navController) }
}
