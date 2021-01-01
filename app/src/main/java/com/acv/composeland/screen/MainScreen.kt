package com.acv.composeland.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.compose.HomeScreen
import com.acv.composeland.core.CoreConceptsMain
import com.acv.composeland.datastate.DataAndStateMain
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.navigation.NavigationMain

sealed class MainScreen(val route: String) {
    object Core : MainScreen("core")
    object Main : MainScreen("main")
    object Material : MainScreen("material")
    object Navigation : MainScreen("navigation")
    object Legacy : MainScreen("legacy")
    object Layout : MainScreen("layout")
    object Theming : MainScreen("theming")
    object DataAndState : MainScreen("data_and_state")
}

fun NavGraphBuilder.main(navController: NavHostController) {
    composable(MainScreen.Core.route) { CoreConceptsMain(navController) }
    composable(MainScreen.Main.route) { HomeScreen(navController) }
    composable(MainScreen.Material.route) { MaterialMain(navController) }
    composable(MainScreen.Navigation.route) { NavigationMain(navController) }
    composable(MainScreen.Legacy.route) { CoreConceptsMain(navController) }
    composable(MainScreen.Layout.route) { CoreConceptsMain(navController) }
    composable(MainScreen.Theming.route) { CoreConceptsMain(navController) }
    composable(MainScreen.DataAndState.route) { DataAndStateMain(navController) }
}