package com.acv.composeland.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.acv.composeland.appbar.bottom.BottomAppBarDependencies
import com.acv.composeland.appbar.bottom.BottomAppBarSamples
import com.acv.composeland.button.ButtonDependencies
import com.acv.composeland.button.ButtonSamples
import com.acv.composeland.chip.ChipDependencies
import com.acv.composeland.chip.ChipSamples
import com.acv.composeland.text.TextDependencies
import com.acv.composeland.text.TextSamples

sealed class Screen(
    val route: String,
    val name: String,
) {

    companion object {
        fun routes(
            textDependencies: TextDependencies,
            buttonDependencies: ButtonDependencies,
            chipDependencies: ChipDependencies,
            bottomAppBarDependencies: BottomAppBarDependencies,
        ) = listOf(
            Text(textDependencies),
            Button(buttonDependencies),
            Chip(chipDependencies),
            BottomAppBar(bottomAppBarDependencies, textDependencies),
        )
    }

    @Composable
    abstract fun screen()

    data class Main(
        val navHostController: NavHostController,
        val dependencies: MainDependencies,
        val textDependencies: TextDependencies,
        val buttonDependencies: ButtonDependencies,
        val chipDependencies: ChipDependencies,
        val bottomAppBarDependencies: BottomAppBarDependencies,
    ) : Screen("main", "Main") {

        @Composable
        override fun screen() {
            NavHost(navController = navHostController, startDestination = route) {
                val routes = routes(textDependencies, buttonDependencies, chipDependencies, bottomAppBarDependencies)
                composable(route) { MainScreen(dependencies) }
                routes.forEach { screen -> composable(screen.route) { screen.screen() } }
            }
        }
    }

    data class Text(
        val dependencies: TextDependencies
    ) : Screen(route, "Text") {
        companion object {
            const val route = "text"
        }

        @Composable
        override fun screen() =
            TextSamples(dependencies)
    }

    data class Button(
        val dependencies: ButtonDependencies
    ) : Screen("button", "Button") {
        @Composable
        override fun screen() =
            ButtonSamples(dependencies)
    }

    data class BottomAppBar(
        val dependencies: BottomAppBarDependencies,
        val textDependencies: TextDependencies,
    ) : Screen(route, "BottomAppBar") {
        companion object {
            const val route = "BottomAppBar"
        }

        @Composable
        override fun screen() =
            BottomAppBarSamples(dependencies)
    }

    data class Chip(val dependencies: ChipDependencies) : Screen("chip", "Chip") {
        @Composable
        override fun screen() =
            ChipSamples(dependencies)
    }
}