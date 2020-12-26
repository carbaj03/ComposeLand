package com.acv.composeland.appbar.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

sealed class BottomAppBarScreen(val route: String) {
    companion object {
        fun routes(
            navController: NavHostController,
        ) = listOf(
            Background(BottomAppBarState { navController.popBackStack() }),
            ContentColor(BottomAppBarContentColorState { navController.popBackStack() }),
            CutoutShape(BottomAppBarCutoutShapeState { navController.popBackStack() }),
            Elevation(BottomAppBarElevationState { navController.popBackStack() }),
        )

        fun items(navController: NavHostController) =
            routes(navController).map {
                BottomAppBarMainItem(
                    name = it.route,
                    goToDetail = { navController.navigate(route = it.route) },
                )
            }
    }

    @Composable
    abstract fun screen()

    data class Main(
        val state: BottomAppBarMainState
    ) : BottomAppBarScreen(route = route) {

        companion object {
            const val route = "BottomAppBarMain"
        }

        @Composable
        override fun screen() {
            BottomAppBarMain(state)
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
        val state: BottomAppBarContentColorState,
    ) : BottomAppBarScreen("BottomAppBarontentColor") {
        @Composable
        override fun screen() {
            BottomAppBarContentColor(state)
        }
    }

    data class CutoutShape(
        val state: BottomAppBarCutoutShapeState
    ) : BottomAppBarScreen("BottomAppBarcutoutShape") {
        @Composable
        override fun screen() {
            BottomAppBarCutoutShape(state)
        }
    }

    data class Elevation(
        val state: BottomAppBarElevationState
    ) : BottomAppBarScreen("BottomAppBarelevation") {
        @Composable
        override fun screen() {
            BottomAppBarElevation(state)
        }
    }

}