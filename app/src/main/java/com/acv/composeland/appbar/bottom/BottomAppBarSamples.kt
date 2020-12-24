package com.acv.composeland.appbar.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.main.Screen

data class BottomAppBarDependencies(
    val title: String,
    val textScreen: Screen.Text,
)

@Composable
fun BottomAppBarSamples(
    dependencies: BottomAppBarDependencies,
) {
    val navController = rememberNavController()
    BottomAppBarScreen.Main(
        navController = navController,
        textScreen = dependencies.textScreen,
    ).screen()
}