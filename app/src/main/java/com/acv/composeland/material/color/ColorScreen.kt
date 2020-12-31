package com.acv.composeland.material.color

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

sealed class ColorScreen(
    val route: String
) {
    companion object {
        fun routes(navController: NavHostController) =
            listOf(
                Palette(
                    PaletteColorState(
                        title = "",
                        goBack = { navController.popBackStack() },
                    )
                ),
            )

        fun items(navController: NavHostController) =
            routes(navController).map {
                ColorMainItem(
                    name = it.route,
                    goToDetail = { navController.navigate(route = it.route) },
                )
            }
    }

    @Composable
    abstract fun screen()

    data class Main(
        val state: ColorMainState,
    ) : ColorScreen(route = route) {
        companion object {
            const val route: String = "MainColor"
        }

        @Composable
        override fun screen() {
            ColorMain(state)
        }
    }

    data class Palette(
        val state: PaletteColorState
    ) : ColorScreen("PaletteColor") {
        @Composable
        override fun screen() {
            PaletteColor(state = state)
        }
    }

}