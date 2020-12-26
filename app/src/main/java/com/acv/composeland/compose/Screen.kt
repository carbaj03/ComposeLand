package com.acv.composeland.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.animation.AnimationMain
import com.acv.composeland.animation.AnimationMainState
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.material.MaterialState
import com.acv.composeland.navigation.NavigationMain
import com.acv.composeland.navigation.NavigationState

sealed class Screen(
    val route: String,
) {
    companion object {
        fun mainItems(navController: NavHostController) =
            listOf(
                MainItem(
                    image = R.drawable.bottom,
                    title = "Material",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = Material.route) },
                ),
                MainItem(
                    image = R.drawable.bottom,
                    title = "Navigation",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = Navigation.route) },
                ),
                MainItem(
                    image = R.drawable.bottom,
                    title = "Animation",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = Animation.route) },
                ),
            )

        fun NavGraphBuilder.main(
            materialState: MaterialState,
            navigationState: NavigationState,
            mainState: MainState,
            animationState: AnimationMainState,
        ) {
            val routes = routes(materialState, navigationState, animationState)
            composable(Main.route) { MainScreen(mainState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        }

        private fun routes(
            materialState: MaterialState,
            navigationState: NavigationState,
            animationState: AnimationMainState,
        ) = listOf(
            Material(materialState),
            Navigation(navigationState),
            Animation(animationState),
        )
    }

    @Composable
    abstract fun screen()

    data class Main(
        val mainState: MainState,
    ) : Screen(route) {
        companion object {
            const val route = "MainApp"
        }

        @Composable
        override fun screen() {
            MainScreen(mainState)
        }
    }

    data class Material(
        val state: MaterialState,
    ) : Screen(route) {
        companion object {
            const val route = "Mainmaterial"
        }

        @Composable
        override fun screen() {
            MaterialMain(state = state)
        }
    }

    data class Navigation(
        val state: NavigationState,
    ) : Screen(route) {
        companion object {
            const val route = "Mainnavigation"
        }

        @Composable
        override fun screen() {
            NavigationMain(state = state)
        }
    }

    data class Animation(
        val state: AnimationMainState,
    ) : Screen(route) {
        companion object {
            const val route = "MainAnimation"
        }

        @Composable
        override fun screen() {
            AnimationMain(state = state)
        }
    }
}