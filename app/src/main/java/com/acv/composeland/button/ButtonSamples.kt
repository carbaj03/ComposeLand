package com.acv.composeland.button

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

data class ButtonDependencies(
    val title: String
)

@Composable
fun ButtonSamples(dependencies: ButtonDependencies) {
    val navController = rememberNavController()
    ButtonScreen.Main(navController = navController).screen()
}