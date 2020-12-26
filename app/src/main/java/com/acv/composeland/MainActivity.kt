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
import com.acv.composeland.animation.AnimationMain
import com.acv.composeland.animation.AnimationMainState
import com.acv.composeland.animation.AnimationScreen
import com.acv.composeland.appbar.bottom.BottomAppBarMain
import com.acv.composeland.appbar.bottom.BottomAppBarMainState
import com.acv.composeland.appbar.bottom.BottomAppBarScreen
import com.acv.composeland.button.ButtonMain
import com.acv.composeland.button.ButtonMainState
import com.acv.composeland.button.ButtonScreen
import com.acv.composeland.chip.ChipMainState
import com.acv.composeland.chip.ChipScreen
import com.acv.composeland.common.Navigator
import com.acv.composeland.compose.MainState
import com.acv.composeland.compose.Screen
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.material.MaterialScreen
import com.acv.composeland.material.MaterialState
import com.acv.composeland.material.PrincipleItem
import com.acv.composeland.material.RelatedItem
import com.acv.composeland.navigation.NavigationScreen
import com.acv.composeland.navigation.NavigationMain
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
        title = "ComposeLand",
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
    val animationState = AnimationMainState(
        title = "Aniamtions",
        goBack = { navController.popBackStack() },
        items = AnimationScreen.items(navController)
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
        goBack = { navController.popBackStack() },
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
        principle = "Principles",
        principles = listOf(
            PrincipleItem(
                image = R.drawable.bottom,
                title = "Actionable",
                subTitle = "Actionable thing",
            ),
            PrincipleItem(
                image = R.drawable.bottom,
                title = "Flexible",
                subTitle = "Flexible description",
            ),
            PrincipleItem(
                image = R.drawable.bottom,
                title = "Ergonomic",
                subTitle = "Ergonomic Description",
            )
        ),
        items = BottomAppBarScreen.items(navController),
    )

    Navigator.Global(
        navController = navController,
        startDestination = Screen.Main.route,
        main = {
            Screen.run { main(materialState, navigationState, mainState, animationState) }
        },
        material = {
            val routes = MaterialScreen.routes(textMainState, buttonMainState, bottomAppBarMainState, chipMainState)
            composable(MaterialScreen.Main.route) { MaterialMain(materialState) }
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
            composable(NavigationScreen.Main.route) { NavigationMain(navigationState) }
            routes.forEach { screen ->
                composable(screen.route) { screen.screen() }
            }
        },
        bottomAppBar = {
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
        },
        animation = {
            val routes = AnimationScreen.routes(navController)
            composable(AnimationScreen.Main.route) { AnimationMain(animationState) }
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