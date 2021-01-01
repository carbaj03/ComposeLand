package com.acv.composeland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.animation.AnimationCrossfade
import com.acv.composeland.animation.AnimationMain
import com.acv.composeland.appbar.bottom.*
import com.acv.composeland.button.*
import com.acv.composeland.chip.ChipMain
import com.acv.composeland.chip.TextColor
import com.acv.composeland.compose.HomeScreen
import com.acv.composeland.core.CoreConceptsMain
import com.acv.composeland.datastate.DataAndStateMain
import com.acv.composeland.material.MaterialMain
import com.acv.composeland.material.color.ColorMain
import com.acv.composeland.navigation.NavigationMain
import com.acv.composeland.screen.*
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

@Composable
fun AppMain() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreen.Main.route
    ) {
        main(navController)
        material(navController)
        navigation(navController)
        text(navController)
        button(navController)
        bottomAppBar(navController)
        animation(navController)
        color(navController)
    }

}

fun NavGraphBuilder.main(navController: NavHostController) {
    composable(MainScreen.Core.route) { CoreConceptsMain(navController) }
    composable(MainScreen.Main.route) { HomeScreen(navController) }
    composable(MainScreen.Material.route) { MaterialMain(navController) }
    composable(MainScreen.Navigation.route) { NavigationMain(navController) }
    composable(MainScreen.Legacy.route) { CoreConceptsMain(navController) }
    composable(MainScreen.Layout.route) { CoreConceptsMain(navController) }
    composable(MainScreen.Theming.route) { CoreConceptsMain(navController) }
    composable(MainScreen.DataAndState.route) { DataAndStateMain(navController) }
}

fun NavGraphBuilder.material(navController: NavHostController) {
    composable(MaterialScreen.Main.route) { MaterialMain(navController = navController) }
    composable(MaterialScreen.Text.route) { TextMain(navController = navController) }
    composable(MaterialScreen.Color.route) { ColorMain(navController = navController) }
    composable(MaterialScreen.Button.route) { ButtonMain(navController = navController) }
    composable(MaterialScreen.Chip.route) { ChipMain(navController = navController) }
    composable(MaterialScreen.BottomAppBar.route) { BottomAppBarMain(navController = navController) }
}

fun NavGraphBuilder.button(navController: NavHostController) {
    composable(ButtonScreen.Main.route) { ButtonMain(navController = navController) }
    composable(ButtonScreen.Color.route) { ButtonColor(navController = navController) }
    composable(ButtonScreen.Click.route) { ButtonClick(navController = navController) }
    composable(ButtonScreen.Enabled.route) { ButtonEnabled(navController = navController) }
    composable(ButtonScreen.InteractionState.route) { ButtonInteractionState(navController = navController) }
    composable(ButtonScreen.Shape.route) { ButtonShape(navController = navController) }
    composable(ButtonScreen.Border.route) { ButtonBorder(navController = navController) }
}

fun NavGraphBuilder.navigation(navController: NavHostController) {
    composable(NavigationScreen.Main.route) { NavigationMain(navController = navController) }
}

fun NavGraphBuilder.bottomAppBar(navController: NavHostController) {
    composable(BottomAppBarScreen.Main.route) { BottomAppBarMain(navController = navController) }
    composable(BottomAppBarScreen.Background.route) { BottomAppBarBackground(navController = navController) }
    composable(BottomAppBarScreen.ContentColor.route) { BottomAppBarContentColor(navController = navController) }
    composable(BottomAppBarScreen.CutoutShape.route) { BottomAppBarCutoutShape(navController = navController) }
    composable(BottomAppBarScreen.Elevation.route) { BottomAppBarElevation(navController = navController) }
}

fun NavGraphBuilder.text(navController: NavHostController) {
    composable(TextScreen.Main.route) { TextMain(navController = navController) }
    composable(TextScreen.Color.route) { TextColor(navController = navController) }
    composable(TextScreen.FontSize.route) { TextFontSize(navController = navController) }
    composable(TextScreen.FontFamily.route) { TextFontFamily(navController = navController) }
    composable(TextScreen.FontStyle.route) { TextFontStyle(navController = navController) }
    composable(TextScreen.FontWeight.route) { TextFontWeight(navController = navController) }
}

fun NavGraphBuilder.animation(navController: NavHostController) {
    composable(AnimationScreen.Main.route) { AnimationMain(navController = navController) }
    composable(AnimationScreen.Crossfade.route) { AnimationCrossfade(navController = navController) }
}

fun NavGraphBuilder.color(navController: NavHostController) {
    composable(ColorScreen.Main.route) { ColorMain(navController = navController) }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLandTheme {
        AppMain()
    }
}