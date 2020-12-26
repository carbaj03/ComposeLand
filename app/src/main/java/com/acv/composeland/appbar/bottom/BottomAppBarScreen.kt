package com.acv.composeland.appbar.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.acv.composeland.chip.ChipMainItem
import com.acv.composeland.chip.ChipScreen
import com.acv.composeland.material.MaterialScreen

sealed class BottomAppBarScreen(val route: String) {
    companion object {
        fun routes(
            navController: NavHostController,
        ) =
            listOf(
                Background(BottomAppBarState { navController.popBackStack() }),
                ContentColor(navController),
                CutoutShape(navController),
                Elevation(navController),
            )

        fun items(navController: NavHostController) =
            BottomAppBarScreen.routes(navController).map {
                BottomAppBarMainItem(
                    name = "it.name",
                    goToDetail = { navController.navigate(route = it.route) },
                )
            }
    }

    @Composable
    abstract fun screen()

    data class Main(
        val navController: NavHostController,
        val textScreen: MaterialScreen.Text,
    ) : BottomAppBarScreen(route = route) {

        companion object {
            const val route = "BottomAppBarMain"
        }

        @Composable
        override fun screen() {
//            NavHost(navController = navController, startDestination = route) {
//                val routes = routes(navController)
//                composable(route) {
//                    val items = routes.map {
//                        BottomAppBarMainItem(
//                            name = "asdf",
//                            goToDetail = { navController.navigate(it.route) },
//                        )
//                    }
//                    BottomAppBarMain(
//                        state = BottomAppBarMainState(
//                            goBack = { navController.popBackStack() },
//                            items = items,
//                            goText = { navController.navigate(textScreen.route) }
//                        )
//                    )
//                }
//                routes.forEach { screen ->
//                    composable(screen.route) { screen.screen() }
//                }
//                composable(textScreen.route) { textScreen.screen() }
//            }
        }
    }

    data class Background(val state: BottomAppBarState) : BottomAppBarScreen(route) {
        companion object {
            const val route = "BottomAppBarBackground"
        }

        @Composable
        override fun screen() {
            BottomAppBarBackground(state)
        }
    }

    data class ContentColor(
        val navController: NavHostController
    ) : BottomAppBarScreen("BottomAppBarontentColor") {
        @Composable
        override fun screen() {
            BottomAppBarContentColor(goBack = { navController.popBackStack() })
        }
    }

    data class CutoutShape(
        val navController: NavHostController
    ) : BottomAppBarScreen("BottomAppBarcutoutShape") {
        @Composable
        override fun screen() {
            BottomAppBarCutoutShape(goBack = { navController.popBackStack() })
        }
    }

    data class Elevation(
        val navController: NavHostController
    ) : BottomAppBarScreen("BottomAppBarelevation") {
        @Composable
        override fun screen() {
            BottomAppBarElevation(goBack = { navController.popBackStack() })
        }
    }

}