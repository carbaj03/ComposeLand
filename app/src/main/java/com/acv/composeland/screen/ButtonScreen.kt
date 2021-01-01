package com.acv.composeland.screen

sealed class ButtonScreen(val route: String) {
    object Main : ButtonScreen("button_main")
    object Color : ButtonScreen("button_color")
    object Click : ButtonScreen("button_click")
    object Enabled : ButtonScreen("button_enabled")
    object InteractionState : ButtonScreen("button_interaction_state")
    object Shape : ButtonScreen("button_shape")
    object Border : ButtonScreen("button_border")
}