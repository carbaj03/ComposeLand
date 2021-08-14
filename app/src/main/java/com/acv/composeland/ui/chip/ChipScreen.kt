package com.acv.composeland.ui.chip

import androidx.navigation.NavHostController

sealed class ChipScreen(val route: String) {
    companion object {
        val startDestination: ChipScreen = Main
        fun routes(navController: NavHostController) = listOf(
            Color
        )

        fun items(navController: NavHostController) =
            ChipScreen.routes(navController).map {
                ChipMainItem(
                    name = "it.name",
                    goToDetail = { navController.navigate(route = it.route) },
                )
            }
    }

    object Main : ChipScreen("main")
    object Color : ChipScreen("color")
}