package com.acv.composeland.ui.animation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

sealed class AnimationScreen(val route: String) {
    object Main : AnimationScreen("animation_main")
    object Visibility : AnimationScreen("animation_visibility")
    object ContentSize : AnimationScreen("animation_content_size")
    object Crossfade : AnimationScreen("animation_crossfade")
    object AsState : AnimationScreen("animation_as_state")
    object Animatable : AnimationScreen("animation_animatable")
    object UpdateTransition : AnimationScreen("animation_update_transition")
    object InfiniteTransition : AnimationScreen("animation_infinite_transition")
    object TargetBased : AnimationScreen("animation_target_based")
    object Spec : AnimationScreen("animation_spec")
}

fun NavGraphBuilder.animation(navController: NavHostController) {
    val navigator = AnimationNavigatorComponent(navController = navController)

    composable(AnimationScreen.Main.route) {
        AnimationMain(navigator = navigator)
    }
    composable(AnimationScreen.Crossfade.route) {
        AnimationCrossfade(navController = navController)
    }
    composable(AnimationScreen.Visibility.route) {
        AnimationVisibility(navController = navController)
    }
    composable(AnimationScreen.ContentSize.route) {
        AnimationContentSize(navController = navController)
    }
    composable(AnimationScreen.AsState.route) {
        AnimationAsState(navController = navController)
    }
    composable(AnimationScreen.Animatable.route) {
        AnimationAnimatable(navController = navController)
    }
    composable(AnimationScreen.UpdateTransition.route) {
        AnimationUpdateTransition(navController = navController)
    }
    composable(AnimationScreen.InfiniteTransition.route) {
        AnimationInfiniteTransition(navController = navController)
    }
    composable(AnimationScreen.TargetBased.route) {
        AnimationTargetBased(navController = navController)
    }
    composable(AnimationScreen.Spec.route) {
        AnimationSpec(navController = navController)
    }

}