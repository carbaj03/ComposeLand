package com.acv.composeland.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.material.MaterialScreen
import com.acv.composeland.material.MaterialState
import com.acv.composeland.navigation.NavigationScreen
import com.acv.composeland.navigation.NavigationState

object Navigator {

    @Composable
    fun global(
        navController: NavHostController,
        startDestination: String,
        main: NavGraphBuilder.(NavHostController) -> Unit,
        material: NavGraphBuilder.() -> Unit,
        button: NavGraphBuilder.() -> Unit,
        text: NavGraphBuilder.() -> Unit,
        navigation: NavGraphBuilder.() -> Unit,
        bottonAppBar: NavGraphBuilder.() -> Unit,
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            main(navController)
            material()
            navigation()
            text()
            button()
            bottonAppBar()
        }
    }
}

sealed class Screen(
    val route: String,
) {
    companion object {

        fun routes(
            materialState: MaterialState,
            navigationState: NavigationState,
        ) = listOf(
            Material(materialState),
            Navigation(navigationState),
        )

        fun mainItems(navController: NavHostController) =
            listOf(
                MainItem(
                    image = R.drawable.bottom,
                    title = "Material",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = Screen.Material.route) },
                ),
                MainItem(
                    image = R.drawable.bottom,
                    title = "Navigation",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = Screen.Navigation.route) },
                ),
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
//            NavHost(navController = navController, startDestination = route) {
//                val routes = routes(materialState, textDependencies, buttonDependencies, chipDependencies, bottomAppBarDependencies, navigationDependencies)
//                composable(route) { MainScreen(mainState) }
//                routes.forEach { screen ->
//                    composable(screen.route) { screen.screen() }
//                }
//            }
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
            MaterialScreen(state = state)
//            MaterialScreen.Main(
//                state, textDependencies, buttonDependencies, chipDependencies, bottomAppBarDependencies
//            ).screen()
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
            NavigationScreen(state = state)
//            NavigationScreen.Main(
//                navController = navController,
//                dependencies = dependencies
//            ).screen()
        }
    }
}