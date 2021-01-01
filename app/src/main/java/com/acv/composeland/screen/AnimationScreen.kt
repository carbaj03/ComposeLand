package com.acv.composeland.screen

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.acv.composeland.animation.AnimationCrossfade
import com.acv.composeland.animation.AnimationMain

sealed class AnimationScreen(val route: String) {
    object Main : AnimationScreen("animation_main")
    object Crossfade : AnimationScreen("animation_crossfade")
}


fun NavGraphBuilder.animation(navController: NavHostController) {
    composable(AnimationScreen.Main.route) { AnimationMain(navController = navController) }
    composable(AnimationScreen.Crossfade.route) { AnimationCrossfade(navController = navController) }
}