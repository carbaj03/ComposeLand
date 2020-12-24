package com.acv.composeland.text

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class TextScreen(val route: String, val name: String) {
    companion object {
        fun routes(navController: NavHostController) =
            listOf(
                Color(navController),
                FontSize(navController),
                FontStyle(navController),
                FontWeight(navController),
                FontFamily(navController),
            )
    }

    @Composable
    abstract fun screen()

    data class Main(val navController: NavHostController) : TextScreen("main", "Main") {
        @Composable
        override fun screen() {
            NavHost(navController = navController, startDestination = route) {
                val routes = routes(navController)
                composable(route) { TextMain(navController = navController, items = routes) }
                routes.forEach { screen -> composable(screen.route) { screen.screen() } }
            }
        }
    }

    data class Color(val navController: NavHostController) : TextScreen("color", "Color") {
        @Composable
        override fun screen() {
            TextColor(goBack = { navController.popBackStack() })
        }
    }

    data class FontSize(val navController: NavHostController) : TextScreen("fontSize", "Font Size") {
        @Composable
        override fun screen() {
            TextFontSize(goBack = { navController.popBackStack() })
        }
    }

    data class FontStyle(val navController: NavHostController) : TextScreen("fontStyle", "Font Style") {
        @Composable
        override fun screen() {
            TextStyle(goBack = { navController.popBackStack() })
        }
    }

    data class FontWeight(val navController: NavHostController) : TextScreen("fontWeight", "Font Weight") {
        @Composable
        override fun screen() {
            TextFontWeight(goBack = { navController.popBackStack() })
        }
    }

    data class FontFamily(val navController: NavHostController) : TextScreen("fontFamily", "Font Family") {
        @Composable
        override fun screen() {
            TextFontFamily(goBack = { navController.popBackStack() })
        }
    }
}