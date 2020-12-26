package com.acv.composeland.animation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

sealed class AnimationScreen(
    val route: String
) {
    companion object {
        fun routes(navController: NavHostController) =
            listOf(
                Crossfade(AnimationCrossfadeState { navController.popBackStack() }),
            )

        fun items(navController: NavHostController) =
            routes(navController).map {
                AnimationMainItem(
                    name = it.route,
                    goToDetail = { navController.navigate(route = it.route) },
                )
            }
    }

    @Composable
    abstract fun screen()

    data class Main(
        val state: AnimationMainState,
    ) : AnimationScreen(route = route) {
        companion object {
            const val route: String = "animationmain"
        }

        @Composable
        override fun screen() {
            AnimationMain(state)
        }
    }

    data class Crossfade(
        val state: AnimationCrossfadeState
    ) : AnimationScreen("animationcrossfade") {
        @Composable
        override fun screen() {
            AnimationCrossfade(state = state)
        }
    }
}