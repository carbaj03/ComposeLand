package com.acv.composeland.ui.common

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface Routes<A : RouteDependencies, B> {
    fun items(navController: NavHostController): List<B>
    fun NavGraphBuilder.main(routeDependencies: A)
}

interface RouteDependencies

fun <A : RouteDependencies, B> NavGraphBuilder.routes(
    screen: Routes<A, B>,
    dependencies: A
) {
    screen.run { main(dependencies) }
}