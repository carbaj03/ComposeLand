package com.acv.composeland.ui.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.ui.compose.HomeScreen
import com.acv.composeland.ui.compose.MainNavigatorComponent
import com.acv.composeland.ui.core.CoreConceptsMain
import com.acv.composeland.ui.datastate.DataAndStateMain
import com.acv.composeland.ui.material.MaterialMain
import com.acv.composeland.ui.navigation.NavigationMain

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

fun NavGraphBuilder.main(
    navController: NavHostController,
) {
    val main =  MainNavigatorComponent(navController)
    composable(MainScreen.Core.route) {
        CoreConceptsMain(navController)
    }
    composable(MainScreen.Main.route) {
        HomeScreen(main)
    }
    composable(MainScreen.Material.route) {
        MaterialMain(navController)
    }
    composable(MainScreen.Navigation.route) {
        NavigationMain(navController)
    }
    composable(MainScreen.Legacy.route) {
        CoreConceptsMain(navController)
    }
    composable(MainScreen.Layout.route) {
        CoreConceptsMain(navController)
    }
    composable(MainScreen.Theming.route) {
        CoreConceptsMain(navController)
    }
    composable(MainScreen.DataAndState.route) {
        DataAndStateMain(navController)
    }
}