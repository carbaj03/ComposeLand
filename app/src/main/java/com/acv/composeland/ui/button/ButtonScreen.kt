//package com.acv.composeland.ui.button
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.navigate
//
//sealed class ButtonScreen(val route: String, val name: String) {
//    companion object {
//        fun routes(navController: NavHostController) =
//            listOf(
//                Color(navController),
//                Click(navController),
//                Enabled(navController),
//                InteractionState(navController),
//                Shape(navController),
//                Border(navController),
//            )
//
//        fun buttonItems(navController: NavHostController) =
//            routes(navController).map {
//                ButtonMainItem(
//                    name = it.name,
//                    goToDetail = { navController.navigate(route = it.route) },
//                )
//            }
//    }
//
//    @Composable
//    abstract fun screen()
//
//    data class Main(
//        val navController: NavHostController
//    ) : ButtonScreen(route, "Main") {
//        companion object {
//            const val route = "ButtonMain"
//        }
//
//        @Composable
//        override fun screen() {
////            NavHost(navController = navController, startDestination = route) {
////                val routes = routes(navController)
////                composable(route) { ButtonMain(navController = navController, items = routes) }
////                routes.forEach { screen -> composable(screen.route) { screen.screen() } }
////            }
//        }
//    }
//
//    data class Color(val navController: NavHostController) : ButtonScreen("color", "Color") {
//        @Composable
//        override fun screen() {
//            ButtonColor(goBack = { navController.popBackStack() })
//        }
//    }
//
//    data class Click(val navController: NavHostController) : ButtonScreen("click", "Click") {
//        @Composable
//        override fun screen() {
//            ButtonClick(goBack = { navController.popBackStack() })
//        }
//    }
//
//    data class Enabled(val navController: NavHostController) : ButtonScreen("enabled", "Enabled") {
//        @Composable
//        override fun screen() {
//            ButtonEnabled(goBack = { navController.popBackStack() })
//        }
//    }
//
//    data class InteractionState(val navController: NavHostController) : ButtonScreen("interactionState", "Interaction State") {
//        @Composable
//        override fun screen() {
//            ButtonInteractionState(goBack = { navController.popBackStack() })
//        }
//    }
//
//    data class Shape(val navController: NavHostController) : ButtonScreen("shape", "Shape") {
//        @Composable
//        override fun screen() {
//            ButtonShape(goBack = { navController.popBackStack() })
//        }
//    }
//
//    data class Border(val navController: NavHostController) : ButtonScreen("border", "Border") {
//        @Composable
//        override fun screen() {
//            ButtonBorder(goBack = { navController.popBackStack() })
//        }
//    }
//}