package com.acv.composeland.ui.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.appbar.bottom.BottomAppBarMain
import com.acv.composeland.ui.button.ButtonMain
import com.acv.composeland.ui.chip.ChipMain
import com.acv.composeland.ui.material.MaterialMain
import com.acv.composeland.ui.material.MaterialNavigatorComponent
import com.acv.composeland.ui.material.color.ColorMain
import com.acv.composeland.ui.text.TextMain

sealed class MaterialScreen(val route: String) {
    object Main : MaterialScreen("material_main")
    object Text : MaterialScreen("material_text")
    object Color : MaterialScreen("material_color")
    object Button : MaterialScreen("material_button")
    object Chip : MaterialScreen("material_chip")
    object BottomAppBar : MaterialScreen("material_bottomappbar")
}

fun NavGraphBuilder.material(navController: NavHostController) {
    val material = MaterialNavigatorComponent(navController = navController)

    composable(MaterialScreen.Main.route) {
        MaterialMain(material)
    }
    composable(MaterialScreen.Text.route) {
        TextMain(navController = navController)
    }
    composable(MaterialScreen.Color.route) {
        ColorMain(navController = navController)
    }
    composable(MaterialScreen.Button.route) {
        ButtonMain(navController = navController)
    }
    composable(MaterialScreen.Chip.route) {
        ChipMain(navController = navController)
    }
    composable(MaterialScreen.BottomAppBar.route) {
        BottomAppBarMain(navController = navController)
    }
}
