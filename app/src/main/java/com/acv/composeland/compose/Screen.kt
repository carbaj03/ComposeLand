package com.acv.composeland.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.animation.AnimationMain
import com.acv.composeland.animation.AnimationMainState
import com.acv.composeland.common.RouteDependencies
import com.acv.composeland.common.Routes
import com.acv.composeland.core.CoreConceptsMain
import com.acv.composeland.core.CoreConceptsMainState
import com.acv.composeland.datastate.DataAndStateMain
import com.acv.composeland.datastate.DataAndStateState
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.material.MaterialState
import com.acv.composeland.navigation.NavigationMain
import com.acv.composeland.navigation.NavigationState

data class ScreenDependencies(
    val mainState: MainState,
    val coreConceptsState: CoreConceptsMainState,
    val materialState: MaterialState,
    val navigationState: NavigationState,
    val animationState: AnimationMainState,
    val dataAndStateState: DataAndStateState,
) : RouteDependencies


interface Item<A> {
    fun item(navController: NavHostController): A
}

interface Dependency

sealed class Screen<A : Dependency>(

) {
    companion object : Routes<ScreenDependencies, MainItem> {
        override fun items(navController: NavHostController): List<MainItem> =
            listOf(
                Core.item(navController),
                Material.item(navController),
                Navigation.item(navController),
                Animation.item(navController),
                Legacy.item(navController),
                Layout.item(navController),
                Theming.item(navController),
                StateData.item(navController),
            )

        override fun NavGraphBuilder.main(routeDependencies: ScreenDependencies) =
            routeDependencies.routes().forEach { screen ->
                composable(screen.route) { screen.screen() }
            }

        private fun ScreenDependencies.routes(): List<Screen> =
            listOf(
                Core(coreConceptsState),
                Main(mainState),
                Material(materialState),
                Navigation(navigationState),
                Animation(animationState),
                Legacy(coreConceptsState),
                Layout(coreConceptsState),
                Theming(coreConceptsState),
                StateData(dataAndStateState),
            )
    }

    @Composable
    abstract fun screen(dependency: A)

    object Main : Screen<MainState>() {
        const val route: String = "AppMain"

        @Composable
        override fun screen(dependency: MainState) {
            MainScreen(dependency)
        }
    }

    data class Core(
        val state: CoreConceptsMainState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "CoreMain"

            override fun item(navController: NavHostController) =
                HeaderMainItem(
                    image = R.drawable.ic_core_cocepts,
                    title = "Core",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            CoreConceptsMain(state = state)
        }
    }

    data class Material(
        val state: MaterialState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "Materialmain"

            override fun item(navController: NavHostController): MainItem =
                MidMainItem(
                    image = R.drawable.ic_layouts,
                    title = "Material",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            MaterialMain(state = state)
        }
    }

    data class Navigation(
        val state: NavigationState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "Navigationmain"

            override fun item(navController: NavHostController): MainItem =
                MidMainItem(
                    image = R.drawable.ic_theming,
                    title = "Navigation",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            NavigationMain(state = state)
        }
    }

    data class Animation(
        val state: AnimationMainState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "Animationmain"

            override fun item(navController: NavHostController) =
                MidMainItem(
                    image = R.drawable.ic_state_data,
                    title = "Animation",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            AnimationMain(state = state)
        }
    }

    data class Layout(
        val state: CoreConceptsMainState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "Layoutmain"

            override fun item(navController: NavHostController) =
                MidMainItem(
                    image = R.drawable.ic_layouts,
                    title = "Layout",
                    description = "Learn how to work with layouts in Compose",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            CoreConceptsMain(state = state)
        }
    }

    data class Theming(
        val state: CoreConceptsMainState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "ThemingMain"

            override fun item(navController: NavHostController) =
                MidMainItem(
                    image = R.drawable.ic_theming,
                    title = "Theming",
                    description = "Learn how to style your application and support light and dark themes.",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            CoreConceptsMain(state = state)
        }
    }

    data class StateData(
        val state: DataAndStateState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "StateDataMain"

            override fun item(navController: NavHostController) =
                MidMainItem(
                    image = R.drawable.ic_state_data,
                    title = "State and data",
                    description = "Learn patterns for state management when using Compose",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            DataAndStateMain(state = state)
        }
    }

    data class Legacy(
        val state: CoreConceptsMainState,
    ) : Screen(route) {
        companion object : Item<MainItem> {
            const val route = "LegacyMain"

            override fun item(navController: NavHostController) =
                MidMainItem(
                    image = R.drawable.ic_compose,
                    title = "Compose in existing apps",
                    description = "Understand how to combine Views and Compose",
                    guide = { navController.navigate(route = route) },
                )
        }

        @Composable
        override fun screen() {
            CoreConceptsMain(state = state)
        }
    }
}