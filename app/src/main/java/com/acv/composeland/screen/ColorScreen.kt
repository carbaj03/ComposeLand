package com.acv.composeland.screen

sealed class ColorScreen(val route: String) {
    object Main : ColorScreen("color_main")
}