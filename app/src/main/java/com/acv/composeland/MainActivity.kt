package com.acv.composeland

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.animation.AnimationMain
import com.acv.composeland.animation.AnimationMainState
import com.acv.composeland.animation.AnimationScreen
import com.acv.composeland.appbar.bottom.BottomAppBarMain
import com.acv.composeland.appbar.bottom.BottomAppBarMainState
import com.acv.composeland.appbar.bottom.BottomAppBarScreen
import com.acv.composeland.appbar.bottom.design.*
import com.acv.composeland.button.ButtonMain
import com.acv.composeland.button.ButtonMainState
import com.acv.composeland.button.ButtonScreen
import com.acv.composeland.chip.ChipMainState
import com.acv.composeland.chip.ChipScreen
import com.acv.composeland.common.Navigator
import com.acv.composeland.common.routes
import com.acv.composeland.compose.MainState
import com.acv.composeland.compose.Screen
import com.acv.composeland.compose.ScreenDependencies
import com.acv.composeland.core.CoreConceptsMainState
import com.acv.composeland.datastate.DataAndStateState
import com.acv.composeland.datastate.Example
import com.acv.composeland.material.*
import com.acv.composeland.material.color.ColorMainState
import com.acv.composeland.material.color.ColorScreen
import com.acv.composeland.navigation.NavigationMain
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
        title = "ComposeLand",
        goBack = { navController.popBackStack() },
        items = Screen.items(navController)
    )
    val materialState = MaterialState(
        title = "Material",
        goBack = { navController.popBackStack() },
        componentsMaterialState = ComponentsMaterialState(MaterialScreen.components(navController)),
        designMaterialState = DesignMaterialState(MaterialScreen.design(navController)),
    )
    val navigationState = NavigationState(
        title = "Navigation",
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

    val colorMainState = ColorMainState(
        title = "Colors",
        goBack = { navController.popBackStack() },
        items = ColorScreen.items(navController),
    )
    val coreState = CoreConceptsMainState(
        title = "Core",
        goBack = { navController.popBackStack() },
    )

    var expanded by savedInstanceState { false }
    var example by remember(expanded) {
        Log.e("example", expanded.toString())
        mutableStateOf(
            Example(
                isExpanded = expanded,
                onExpand = { expanded = !expanded }
            )
        )
    }
    var dataAndStateState by remember(example) {
        Log.e("dataAndStateState", example.isExpanded.toString())
        mutableStateOf(
            DataAndStateState(
                title = "Core",
                goBack = { navController.popBackStack() },
                example = example
            )
        )
    }

    var usageExpanded by remember { mutableStateOf(false) }
    var fabVisible by remember { mutableStateOf(false) }

    var bottomAppBarMainState by remember {
        mutableStateOf(
            BottomAppBarMainState(
                title = "Bottom App Bar Examples",
                goBack = { navController.popBackStack() },
                description = "A bottom app bar displays navigation and key actions at the bottom of mobile screens.",
                design = DesignState(
                    usage = Usage(
                        isExpanded = usageExpanded,
                        onExpand = {
                            usageExpanded = !usageExpanded
                            Log.e("onexpand", usageExpanded.toString())
                        },
                        title = usageExpanded.toString(),
                        description = "Bottom app bars provide access to a bottom navigation drawer and up to four actions, including the floating action button.",
                        related = listOf(
                            RelatedItem(
                                action = { navController.navigate(route = MaterialScreen.Text.route) },
                                title = "Navigation Drawer",
                                icon = R.drawable.topbar,
                                subTitle = "Navigation drawers provide access to destinations in your app.",
                                type = "Related Article",
                            ),
                            RelatedItem(
                                action = { navController.navigate(route = MaterialScreen.Button.route) },
                                title = "Button Samples",
                                icon = R.drawable.navigation_drawer,
                                subTitle = "All The text samples",
                                type = "Related Article"
                            )
                        ),
                        principles = listOf(
                            PrincipleItem(
                                image = R.drawable.actionable,
                                title = "Actionable",
                                subTitle = "Bottom app bars highlight important screen actions and raise awareness of the floating action button.",
                            ),
                            PrincipleItem(
                                image = R.drawable.flexible,
                                title = "Flexible",
                                subTitle = "A bottom app bar's layout and actions change based on the needs of the screen.",
                            ),
                            PrincipleItem(
                                image = R.drawable.ergonomic,
                                title = "Ergonomic",
                                subTitle = "The bottom app bar is easy to reach from a handheld position on a mobile device.",
                            )
                        ),
                        whenToUsage = "Use always"
                    ),
                    anatomy = Anatomy(
                        title = "Anatomy",
                        description = "Bottom app bars can contain actions that apply to the context of the current screen. They include a navigation menu control on the far left and a floating action button (when one is present). If included in a bottom app bar, an overflow menu control is placed at the end of other actions.",
                        positioning = "Positioning",
                        fab = "Bottom app bars have three different layouts based on the presence of a FAB and its position in the bar. These layouts dictate the number of actions that can be included in the bar.",
                    ),
                    behaviour = Behaviour(
                        title = "Behaviour",
                        layout = Layout(
                            title = "Layout",
                            description = "To support the intent of different sections of an app, the layout and actions of a bottom app bar can be changed to suit each screen.\n" +
                                    "\n" +
                                    "For example, screens can display more or fewer actions according to what suits the screen content best",
                        ),
                        elevation = "",
                        placement = "",
                        scolling = Scrolling(
                            title = "Scrolling",
                            description = "Upon scroll, the bottom app bar can appear or disappear:\n" +
                                    "\n" +
                                    "Scrolling downward hides the bottom app bar. If a FAB is present, it detaches from the bar and remains on screen.\n" +
                                    "Scrolling upward reveals the bottom app bar, and reattaches to a FAB if one is present.\n" +
                                    "A bottom app bar can contain a shape, such as a notch, along its edge to accommodate the FAB. As the bar detaches from the FAB, the bar returns to its default shape. Upon returning to the screen and reattaching to the FAB, the bar regains its notched shape.",
                        )
                    ),
                    theming = Theming(R.drawable.ic_compose),
                    specs = Specs(image = R.drawable.ic_compose),
                ),
                items = BottomAppBarScreen.items(navController),
                onFabClick = { fabVisible = usageExpanded },
                fabVisible = fabVisible,
            )
        )
    }

    var dependencies by remember(dataAndStateState) {
        Log.e("dependencies", dataAndStateState.example.isExpanded.toString())
        mutableStateOf(ScreenDependencies(mainState, coreState, materialState, navigationState, animationState, dataAndStateState))
    }

//    NavHost(
//        navController = navController,
//        startDestination = Screen.Main.route,
//    ) {
//        routes(
//            screen = Screen.Companion,
//            dependencies = dependencies
//        )
//    }

    Navigator().Global(
        navController = navController,
        startDestination = Screen.Main.route,
        main = {
            Log.e("global", dependencies.dataAndStateState.example.isExpanded.toString())
            routes(
                screen = Screen.Companion,
                dependencies = dependencies
            )
        },
        material = {
            val routes = MaterialScreen.routes(textMainState, buttonMainState, bottomAppBarMainState, chipMainState, colorMainState)
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
        },
        color = {
            val routes = ColorScreen.routes(navController)
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