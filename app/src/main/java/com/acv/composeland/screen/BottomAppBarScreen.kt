package com.acv.composeland.screen

sealed class BottomAppBarScreen(val route: String) {
    object Main : BottomAppBarScreen("bottomappbar_main")
    object Background : BottomAppBarScreen("bottomappbar_background")
    object ContentColor : BottomAppBarScreen("bottomappbar_content_color")
    object CutoutShape : BottomAppBarScreen("bottomappbar_cutout_shape")
    object Elevation : BottomAppBarScreen("bottomappbar_elevation")
}