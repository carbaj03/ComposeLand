package com.acv.composeland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.appbar.bottom.BottomAppBarMain
import com.acv.composeland.appbar.bottom.BottomAppBarMainState
import com.acv.composeland.appbar.bottom.BottomAppBarScreen
import com.acv.composeland.appbar.bottom.RelatedItem
import com.acv.composeland.button.ButtonMain
import com.acv.composeland.button.ButtonMainState
import com.acv.composeland.button.ButtonScreen
import com.acv.composeland.chip.ChipMainState
import com.acv.composeland.chip.ChipScreen
import com.acv.composeland.common.Navigator
import com.acv.composeland.compose.MainState
import com.acv.composeland.compose.Screen
import com.acv.composeland.material.MaterialScreen
import com.acv.composeland.material.MaterialState
import com.acv.composeland.navigation.NavigationScreen
import com.acv.composeland.navigation.NavigationState
import com.acv.composeland.text.TextMain
import com.acv.composeland.text.TextMainState
import com.acv.composeland.text.TextScreen
import com.acv.composeland.ui.ComposeLandTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLandTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppMain()
                }
            }
        }
    }
}

@Composable
fun AppMain() {

    val navController = rememberNavController()

    val mainState = MainState(
        goBack = { navController.popBackStack() },
        items = Screen.mainItems(navController)
    )
    val materialState = MaterialState(
        goBack = { navController.popBackStack() },
        items = MaterialScreen.materialItems(navController)
    )
    val navigationState = NavigationState(
        goBack = { navController.popBackStack() },
        items = NavigationScreen.navigationItems(navController)
    )
    val buttonMainState = ButtonMainState(
        title = "Button Examples",
        goBack = { navController.popBackStack() },
        items = ButtonScreen.buttonItems(navController),
    )
    val textMainState = TextMainState(
        title = "Text Examples",
        goBack = { navController.popBackStack() },
        items = TextScreen.textItems(navController),
    )
    val chipMainState = ChipMainState(
        goBack = { navController.popBackStack() },
        items = ChipScreen.items(navController),
    )

    val bottomAppBarMainState = BottomAppBarMainState(
        title = "Bottom App Bar Examples",
        description = "A bottom app bar displays navigation and key actions at the bottom of mobile screens.",
        usage = "Usage",
        usageDescription = "Bottom app bars provide access to a bottom navigation drawer and up to four actions, including the floating action button.",
        related = listOf(
            RelatedItem(
                action = { navController.navigate(route = MaterialScreen.Text.route) },
                title = "Text Samples",
                subTitle = "All The text samples"
            ),
            RelatedItem(
                action = { navController.navigate(route = MaterialScreen.Button.route) },
                title = "Button Samples",
                subTitle = "All The text samples"
            )
        ),
        goBack = { navController.popBackStack() },
        items = BottomAppBarScreen.items(navController),
    )

    Navigator.Global(
        navController = navController,
        startDestination = Screen.Main.route,
        main = {
            Screen.run { main(materialState, navigationState, mainState) }
        },
        material = {
            val routes = MaterialScreen.routes(textMainState, buttonMainState, bottomAppBarMainState, chipMainState)
            composable(MaterialScreen.Main.route) { MaterialScreen(materialState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        },
        button = {
            val routes = ButtonScreen.routes(navController)
            composable(ButtonScreen.Main.route) { ButtonMain(buttonMainState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        },
        navigation = {
            val routes = NavigationScreen.routes()
            composable(NavigationScreen.Main.route) { NavigationScreen(navigationState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        },
        bottonAppBar = {
            val routes = BottomAppBarScreen.routes(navController)
            composable(BottomAppBarScreen.Main.route) { BottomAppBarMain(bottomAppBarMainState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        },
        text = {
            val routes = TextScreen.routes(navController)
            composable(TextScreen.Main.route) { TextMain(textMainState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLandTheme {
        AppMain()
    }
}