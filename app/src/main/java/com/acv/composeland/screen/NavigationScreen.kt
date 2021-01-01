package com.acv.composeland.screen

sealed class NavigationScreen(val route: String) {
    object Main : NavigationScreen("navigation_main")
}