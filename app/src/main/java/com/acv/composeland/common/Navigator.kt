package com.acv.composeland.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

object Navigator {

    sealed class Screens {
        object Main : Screens()
        object Material : Screens()
        object Button : Screens()
        object Text : Screens()
        object Navigation : Screens()
        object Animation : Screens()
        object BottomAppBar : Screens()

        val screens = listOf(
            Main, Material, Button, Text, Navigation, Animation, BottomAppBar
        )
    }


    @Composable
    fun Global(
        navController: NavHostController,
        startDestination: String,
        main: NavGraphBuilder.(NavHostController) -> Unit,
        material: NavGraphBuilder.() -> Unit,
        button: NavGraphBuilder.() -> Unit,
        text: NavGraphBuilder.() -> Unit,
        navigation: NavGraphBuilder.() -> Unit,
        animation: NavGraphBuilder.() -> Unit,
        bottomAppBar: NavGraphBuilder.() -> Unit,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {

            main(navController)
            material()
            navigation()
            text()
            button()
            bottomAppBar()
            animation()
        }
    }
}