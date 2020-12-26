package com.acv.composeland.text

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

sealed class TextScreen(
    val route: String
) {
    companion object {
        fun routes(navController: NavHostController) =
            listOf(
                Color(TextColorState { navController.popBackStack() }),
                FontSize(navController),
                FontStyle(navController),
                FontWeight(navController),
                FontFamily(navController),
            )

        fun textItems(navController: NavHostController) =
            routes(navController).map {
                TextMainItem(
                    name = it.route,
                    goToDetail = { navController.navigate(route = it.route) },
                )
            }
    }

    @Composable
    abstract fun screen()

    data class Main(
        val state: TextMainState,
    ) : TextScreen(route = route) {
        companion object {
            const val route: String = "Textmain"
        }

        @Composable
        override fun screen() {
            TextMain(state)
        }
    }

    data class Color(
        val state: TextColorState
    ) : TextScreen("Textcolor") {
        @Composable
        override fun screen() {
            TextColor(state = state)
        }
    }

    data class FontSize(
        val navController: NavHostController
    ) : TextScreen("TextfontSize") {
        @Composable
        override fun screen() {
            TextFontSize(goBack = { navController.popBackStack() })
        }
    }

    data class FontStyle(
        val navController: NavHostController
    ) : TextScreen("TextfontStyle") {
        @Composable
        override fun screen() {
            TextStyle(goBack = { navController.popBackStack() })
        }
    }

    data class FontWeight(
        val navController: NavHostController
    ) : TextScreen("TextfontWeight") {
        @Composable
        override fun screen() {
            TextFontWeight(goBack = { navController.popBackStack() })
        }
    }

    data class FontFamily(
        val navController: NavHostController
    ) : TextScreen("TextfontFamily") {
        @Composable
        override fun screen() {
            TextFontFamily(goBack = { navController.popBackStack() })
        }
    }
}