package com.acv.composeland.material

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.appbar.bottom.BottomAppBarMain
import com.acv.composeland.appbar.bottom.BottomAppBarMainState
import com.acv.composeland.button.ButtonMain
import com.acv.composeland.button.ButtonMainState
import com.acv.composeland.chip.ChipMain
import com.acv.composeland.chip.ChipMainState
import com.acv.composeland.text.TextMain
import com.acv.composeland.text.TextMainState


sealed class MaterialScreen(
    val route: String,
) {

    companion object {
        fun routes(
            textMainState: TextMainState,
            buttonMainState: ButtonMainState,
            bottomAppBarMainState: BottomAppBarMainState,
            chipMainState: ChipMainState
        ) = listOf(
            Text(textMainState),
            Button(buttonMainState),
            Chip(chipMainState),
            BottomAppBar(bottomAppBarMainState),
        )

        fun materialItems(navController: NavHostController) =
            listOf(
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "App bars: bottom",
                    description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
                    goToDetail = { navController.navigate(route = BottomAppBar.route) },
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "App bars: top",
                    description = "The top app bar displays information and actions relating to the current screen"
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Bottom navigation",
                    description = "Bottom navigation bars allow movement between primary destinations in an app"
                ),

                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Buttons",
                    description = "Buttons allow users to take actions, and make choices, with a single tap "
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Buttons: floating action button",
                    description = "A floating action button (FAB) represents the primary action of a screen "
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Cards",
                    description = "Cards contain content and actions about a single subject"
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Checkboxes",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Chips",
                    description = "Chips are compact elements that represent an input, attribute, or action ",
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Checkboxes",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Text",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = Text.route) },
                ),
            )
    }

    @Composable
    abstract fun screen()

    data class Main(
        val state: MaterialState,
    ) : MaterialScreen(route) {
        companion object {
            const val route = "Materialmain"
        }

        @Composable
        override fun screen() {
            MaterialScreen(state)
        }
    }

    data class Text(
        val state: TextMainState
    ) : MaterialScreen(route) {
        companion object {
            const val route = "Materialtext"
        }

        @Composable
        override fun screen() =
            TextMain(state)
    }

    data class Button(
        val state: ButtonMainState
    ) : MaterialScreen(route) {
        companion object {
            const val route = "Materialbutton"
        }

        @Composable
        override fun screen() =
            ButtonMain(state = state)
    }

    data class BottomAppBar(
        val state: BottomAppBarMainState
    ) : MaterialScreen(route) {
        companion object {
            const val route = "MaterialBottomAppBar"
        }

        @Composable
        override fun screen() {
            BottomAppBarMain(state = state)
        }
    }

    data class Chip(
        val state: ChipMainState
    ) : MaterialScreen("Materialchip") {
        @Composable
        override fun screen() =
            ChipMain(state)
    }
}