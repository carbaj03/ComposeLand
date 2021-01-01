package com.acv.composeland.screen

sealed class TextScreen(val route: String) {
    object Main : TextScreen("text_main")
    object Color : TextScreen("text_color")
    object FontSize : TextScreen("text_font_size")
    object FontStyle : TextScreen("text_font_style")
    object FontFamily : TextScreen("text_font_family")
    object FontWeight : TextScreen("text_font_weight")
}