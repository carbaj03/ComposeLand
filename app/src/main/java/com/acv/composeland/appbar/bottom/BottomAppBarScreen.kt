package com.acv.composeland.appbar.bottom

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.acv.composeland.main.Screen

sealed class BottomAppBarScreen(val route: String, val name: String) {
    companion object {
        fun routes(
            navController: NavHostController,
        ) =
            listOf(
                Background(navController),
                ContentColor(navController),
                CutoutShape(navController),
                Elevation(navController),
            )
    }

    @Composable
    abstract fun screen()

    data class Main(
        val navController: NavHostController,
        val textScreen: Screen.Text,
    ) : BottomAppBarScreen("main", "Main") {
        @Composable
        override fun screen() {
            NavHost(navController = navController, startDestination = route) {
                val routes = routes(navController)
                composable(route) {
                    val items = routes.map {
                        BottomAppBarMainItem(
                            goToDetail = { navController.navigate(it.route) },
                            name = it.name
                        )
                    }
                    BottomAppBarMain(
                        model = BottomAppBarMainModel(
                            goBack = {
                                Log.e("main", "onclick")
                                navController.popBackStack()
                            },
                            items = items,
                            goText = { navController.navigate(textScreen.route) }
                        )
                    )
                }
                routes.forEach { screen ->
                    composable(screen.route) { screen.screen() }
                }
                composable(textScreen.route) { textScreen.screen() }
            }
        }
    }

    data class Background(val navController: NavHostController) : BottomAppBarScreen("Background", "Background") {
        @Composable
        override fun screen() {
            BottomAppBarBackground(goBack = {
                Log.e("main","onclick")
                navController.popBackStack()
            })
        }
    }

    data class ContentColor(val navController: NavHostController) : BottomAppBarScreen("contentColor", "Color") {
        @Composable
        override fun screen() {
            BottomAppBarContentColor(goBack = { navController.popBackStack() })
        }
    }

    data class CutoutShape(val navController: NavHostController) : BottomAppBarScreen("cutoutShape", "CutoutShape") {
        @Composable
        override fun screen() {
            BottomAppBarCutoutShape(goBack = { navController.popBackStack() })
        }
    }

    data class Elevation(val navController: NavHostController) : BottomAppBarScreen("elevation", "Elevation") {
        @Composable
        override fun screen() {
            BottomAppBarElevation(goBack = { navController.popBackStack() })
        }
    }

}