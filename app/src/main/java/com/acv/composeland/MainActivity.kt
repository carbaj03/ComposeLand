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

sealed class MainScreen(val route : String){
    object Core : MainScreen("core")
    object Main : MainScreen("main")
    object Material : MainScreen("material")
    object Navigation : MainScreen("navigation")
    object Legacy : MainScreen("legacy")
    object Layout : MainScreen("layout")
    object Theming: MainScreen("theming")
    object DataAndState : MainScreen("data_and_state")
}

@Composable
fun AppMain() {

    val navController = rememberNavController()

    Navigator().Global(
        navController = navController,
        startDestination = "main",
        main = {
            composable("core") { CoreConceptsMain(navController) }
            composable("main") { MainScreen(navController) }
            composable("material") { MaterialMain(navController) }
            composable("navigation") { NavigationMain(navController) }
            composable("legacy") { CoreConceptsMain(navController) }
            composable("layout") { CoreConceptsMain(navController) }
            composable("theming") { CoreConceptsMain(navController) }
            composable("data_and_state") { DataAndStateMain(navController) }
        },
        material = {
            composable("material_main") { MaterialMain(navController) }
            composable("material_text") { TextMain(navController = navController) }
            composable("material_color") { ColorMain(navController = navController) }
            composable("material_button") { ButtonMain(navController = navController) }
            composable("material_chip") { ChipMain(navController = navController) }
            composable("material_bottomappbar") { BottomAppBarMain(navController = navController) }
        },
        button = {
            composable("button_main") { ButtonMain(navController = navController) }
            composable("button_color") { ColorMain(navController = navController) }
            composable("button_click") { ButtonClick(goBack = { /*TODO*/ }) }
            composable("button_enabled") { ButtonEnabled(goBack = { /*TODO*/ }) }
            composable("button_interaction_state") { ButtonInteractionState(goBack = { /*TODO*/ }) }
            composable("button_shape") { ButtonShape(goBack = { /*TODO*/ }) }
            composable("button_border") { ButtonBorder(goBack = { /*TODO*/ }) }
        },
        navigation = {
            composable("navigation_main") { NavigationMain(navController = navController) }
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