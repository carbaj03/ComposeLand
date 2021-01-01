package com.acv.composeland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.animation.AnimationCrossfade
import com.acv.composeland.animation.AnimationMain
import com.acv.composeland.appbar.bottom.*
import com.acv.composeland.button.*
import com.acv.composeland.chip.ChipMain
import com.acv.composeland.chip.TextColor
import com.acv.composeland.common.Navigator
import com.acv.composeland.compose.MainScreen
import com.acv.composeland.core.CoreConceptsMain
import com.acv.composeland.datastate.DataAndStateMain
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.material.color.ColorMain
import com.acv.composeland.navigation.NavigationMain
import com.acv.composeland.text.*
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

sealed class MainScreen(val route: String) {
    object Core : MainScreen("core")
    object Main : MainScreen("main")
    object Material : MainScreen("material")
    object Navigation : MainScreen("navigation")
    object Legacy : MainScreen("legacy")
    object Layout : MainScreen("layout")
    object Theming : MainScreen("theming")
    object DataAndState : MainScreen("data_and_state")
}

sealed class MaterialScreen(val route: String) {
    object Main : MaterialScreen("material_main")
    object Text : MaterialScreen("material_text")
    object Color : MaterialScreen("material_color")
    object Button : MaterialScreen("material_button")
    object Chip : MaterialScreen("material_chip")
    object BottomAppBar : MaterialScreen("material_bottomappbar")
}

sealed class ButtonScreen(val route: String) {
    object Main : ButtonScreen("button_main")
    object Color : ButtonScreen("button_color")
    object Click : ButtonScreen("button_click")
    object Enabled : ButtonScreen("button_enabled")
    object InteractionState : ButtonScreen("button_interaction_state")
    object Shape : ButtonScreen("button_shape")
    object Border : ButtonScreen("button_border")
}

sealed class NavigationScreen(val route: String) {
    object Main : NavigationScreen("navigation_main")
}

@Composable
fun AppMain() {

    val navController = rememberNavController()

    Navigator().Global(
        navController = navController,
        startDestination = MainScreen.Main.route,
        main = {
            composable(MainScreen.Core.route) { CoreConceptsMain(navController) }
            composable(MainScreen.Main.route) { MainScreen(navController) }
            composable(MainScreen.Material.route) { MaterialMain(navController) }
            composable(MainScreen.Navigation.route) { NavigationMain(navController) }
            composable(MainScreen.Legacy.route) { CoreConceptsMain(navController) }
            composable(MainScreen.Layout.route) { CoreConceptsMain(navController) }
            composable(MainScreen.Theming.route) { CoreConceptsMain(navController) }
            composable(MainScreen.DataAndState.route) { DataAndStateMain(navController) }
        },
        material = {
            composable(MaterialScreen.Main.route) { MaterialMain(navController = navController) }
            composable(MaterialScreen.Text.route) { TextMain(navController = navController) }
            composable(MaterialScreen.Color.route) { ColorMain(navController = navController) }
            composable(MaterialScreen.Button.route) { ButtonMain(navController = navController) }
            composable(MaterialScreen.Chip.route) { ChipMain(navController = navController) }
            composable(MaterialScreen.BottomAppBar.route) { BottomAppBarMain(navController = navController) }
        },
        button = {
            composable(ButtonScreen.Main.route) { ButtonMain(navController = navController) }
            composable(ButtonScreen.Color.route) { ColorMain(navController = navController) }
            composable(ButtonScreen.Click.route) { ButtonClick(goBack = { navController.popBackStack() }) }
            composable(ButtonScreen.Enabled.route) { ButtonEnabled(goBack = { navController.popBackStack() }) }
            composable(ButtonScreen.InteractionState.route) { ButtonInteractionState(goBack = { navController.popBackStack() }) }
            composable(ButtonScreen.Shape.route) { ButtonShape(goBack = { navController.popBackStack() }) }
            composable(ButtonScreen.Border.route) { ButtonBorder(goBack = { navController.popBackStack() }) }
        },
        navigation = {
            composable(NavigationScreen.Main.route) { NavigationMain(navController = navController) }
        },
        bottomAppBar = {
            composable("bottomappbar_main") { BottomAppBarMain(navController = navController) }
            composable("bottomappbar_background") { BottomAppBarBackground(navController = navController) }
            composable("bottomappbar_content_color") { BottomAppBarContentColor(navController = navController) }
            composable("bottomappbar_cutout_shape") { BottomAppBarCutoutShape(navController = navController) }
            composable("bottomappbar_elevation") { BottomAppBarElevation(navController = navController) }
        },
        text = {
            composable("text_main") { TextMain(navController = navController) }
            composable("text_color") { TextColor(navController = navController) }
            composable("text_font_size") { TextFontSize(navController = navController) }
            composable("text_font_family") { TextFontFamily(navController = navController) }
            composable("text_font_style") { TextFontStyle(navController = navController) }
            composable("text_font_weight") { TextFontWeight(navController = navController) }
        },
        animation = {
            composable("animation_main") { AnimationMain(navController = navController) }
            composable("animation_creossfade") { AnimationCrossfade(navController = navController) }
        },
        color = {
//            val routes = ColorScreen.routes(navController)
            composable("color_") {}
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