package com.acv.composeland.chip

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.chip.ChipScreen.Color
import com.acv.composeland.chip.ChipScreen.Companion.startDestination
import com.acv.composeland.chip.ChipScreen.Main

sealed class ChipScreen(val route: String) {
    companion object {
        val startDestination: ChipScreen = Main
    }

    object Main : ChipScreen("main")
    object Color : ChipScreen("color")
}

data class ChipDependencies(
    val title: String
)

@Composable
fun ChipSamples(dependencies: ChipDependencies) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination.route) {
        composable(Main.route) { ChipMain(navController) }
        composable(Color.route) {  }
    }
}