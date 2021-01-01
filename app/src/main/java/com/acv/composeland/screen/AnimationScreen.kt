package com.acv.composeland.screen

sealed class AnimationScreen(val route: String) {
    object Main : AnimationScreen("animation_main")
    object Crossfade : AnimationScreen("animation_crossfade")
}