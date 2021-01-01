package com.acv.composeland.screen

sealed class MaterialScreen(val route: String) {
    object Main : MaterialScreen("material_main")
    object Text : MaterialScreen("material_text")
    object Color : MaterialScreen("material_color")
    object Button : MaterialScreen("material_button")
    object Chip : MaterialScreen("material_chip")
    object BottomAppBar : MaterialScreen("material_bottomappbar")
}