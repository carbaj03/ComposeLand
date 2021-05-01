package com.acv.composeland.ui.animation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

class AnimationNavigatorComponent(
    private val navController: NavHostController
) : AnimationNavigator {
    override fun goBack() {
        navController.popBackStack()
    }

    override fun goAnimationVisibility() {
        navController.navigate(route = AnimationScreen.Visibility.route)
    }

    override fun goModifier() {
        navController.navigate(route = AnimationScreen.ContentSize.route)
    }

    override fun goCrossfade() {
        navController.navigate(route = AnimationScreen.Crossfade.route)
    }

    override fun goRememberInfiniteTransition() {
        navController.navigate(route = AnimationScreen.InfiniteTransition.route)
    }

    override fun goUpdateTransition() {
        navController.navigate(route = AnimationScreen.UpdateTransition.route)
    }

    override fun goAnimateAsState() {
        navController.navigate(route = AnimationScreen.AsState.route)
    }

    override fun goAnimatable() {
        navController.navigate(route = AnimationScreen.Animatable.route)
    }

    override fun goAnimationState() {
        TODO("Not yet implemented")
    }

    override fun goAnimate() {
        TODO("Not yet implemented")
    }

    override fun goTargetBased() {
        navController.navigate(route = AnimationScreen.TargetBased.route)
    }

    override fun goSpec() {
        navController.navigate(route = AnimationScreen.Spec.route)
    }
}