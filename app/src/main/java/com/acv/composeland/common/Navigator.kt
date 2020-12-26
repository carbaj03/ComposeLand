package com.acv.composeland.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

object Navigator {

    @Composable
    fun Global(
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