package com.acv.composeland.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R

sealed class NavigationScreen(
    val route: String,
) {

    companion object {
        fun routes() = listOf(
            Simple("textDependencie"),
        )

        fun navigationItems(navController: NavHostController) = listOf(
            NavigationItem(
                image = R.drawable.bottom,
                title = "Simple",
                description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                goToDetail = { navController.navigate(route = com.acv.composeland.compose.Screen.Material.route) },
            ),
        )
    }

    @Composable
    abstract fun screen()

    data class Main(
        val navController: NavHostController,
        val dependencies: NavigationState,
    ) : NavigationScreen(route) {
        companion object {
            val route: String = "Navigation"
        }

        @Composable
        override fun screen() {
//            NavHost(navController = navController, startDestination = route) {
//                val routes = routes()
//                composable(route) { NavigationScreen(dependencies) }
//                routes.forEach { screen -> composable(screen.route) { screen.screen() } }
//            }
        }
    }

    sealed class Screen(val route: String, @StringRes val resourceId: Int) {
        object Profile : Screen("profile", com.acv.composeland.R.string.profile)
        object Dashboard : Screen("dashboard", com.acv.composeland.R.string.dashboard)
        object Scrollable : Screen("scrollable", com.acv.composeland.R.string.scrollable)
    }

    data class Simple(
        val s: String,
    ) : NavigationScreen(route) {
        companion object {
            const val route = "Navigationtext"
        }

        @Composable
        override fun screen() {
//            NavHost(navController, startDestination = Screen.Profile.route) {
//                composable(Screen.Profile.route) { Profile(navController) }
//                composable(Screen.Dashboard.route) { Dashboard(navController) }
//                composable(Screen.Scrollable.route) { Scrollable(navController) }
//            }
        }
    }

    @Composable
    fun Profile(navController: NavHostController) {
        Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
            Text(text = stringResource(Screen.Profile.resourceId))
            NavigateButton(stringResource(Screen.Dashboard.resourceId)) {
                navController.navigate(Screen.Dashboard.route)
            }
            Divider(color = Color.Black)
            NavigateButton(stringResource(Screen.Scrollable.resourceId)) {
                navController.navigate(Screen.Scrollable.route)
            }
            Spacer(Modifier.weight(1f))
            NavigateBackButton(navController)
        }
    }

    @Composable
    fun Dashboard(navController: NavController, title: String? = null) {
        Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
            Text(text = title ?: stringResource(Screen.Dashboard.resourceId))
            Spacer(Modifier.weight(1f))
            NavigateBackButton(navController)
        }
    }

    @Composable
    fun Scrollable(navController: NavController) {
        Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
            NavigateButton(stringResource(Screen.Dashboard.resourceId)) {
                navController.navigate(Screen.Dashboard.route)
            }
            ScrollableColumn(Modifier.weight(1f)) {
                phrases.forEach { phrase ->
                    Text(phrase, fontSize = 30.sp)
                }
            }
            NavigateBackButton(navController)
        }
    }

    @Composable
    fun NavigateButton(
        text: String,
        listener: () -> Unit = { }
    ) {
        Button(
            onClick = listener,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Navigate to $text")
        }
    }

    @Composable
    fun NavigateBackButton(navController: NavController) {
        if (navController.previousBackStackEntry != null) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Go to Previous screen")
            }
        }
    }

    private val phrases = listOf(
        "Easy As Pie",
        "Wouldn't Harm a Fly",
        "No-Brainer",
        "Keep On Truckin'",
        "An Arm and a Leg",
        "Down To Earth",
        "Under the Weather",
        "Up In Arms",
        "Cup Of Joe",
        "Not the Sharpest Tool in the Shed",
        "Ring Any Bells?",
        "Son of a Gun",
        "Hard Pill to Swallow",
        "Close But No Cigar",
        "Beating a Dead Horse",
        "If You Can't Stand the Heat, Get Out of the Kitchen",
        "Cut To The Chase",
        "Heads Up",
        "Goody Two-Shoes",
        "Fish Out Of Water",
        "Cry Over Spilt Milk",
        "Elephant in the Room",
        "There's No I in Team",
        "Poke Fun At",
        "Talk the Talk",
        "Know the Ropes",
        "Fool's Gold",
        "It's Not Brain Surgery",
        "Fight Fire With Fire",
        "Go For Broke"
    )


}

