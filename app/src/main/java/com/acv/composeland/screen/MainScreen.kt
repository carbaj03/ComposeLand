package com.acv.composeland.screen

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