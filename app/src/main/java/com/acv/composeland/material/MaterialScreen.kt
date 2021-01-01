//package com.acv.composeland.material
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.navigate
//import com.acv.composeland.R
//import com.acv.composeland.appbar.bottom.BottomAppBarMain
//import com.acv.composeland.appbar.bottom.BottomAppBarMainState
//import com.acv.composeland.button.ButtonMain
//import com.acv.composeland.button.ButtonMainState
//import com.acv.composeland.chip.ChipMain
//import com.acv.composeland.chip.ChipMainState
//import com.acv.composeland.material.color.ColorMain
//import com.acv.composeland.material.color.ColorMainState
//import com.acv.composeland.text.TextMain
//import com.acv.composeland.text.TextMainState
//
//
//sealed class MaterialScreen(
//    val route: String,
//) {
//
//    companion object {
//        fun routes(
//            textMainState: TextMainState,
//            buttonMainState: ButtonMainState,
//            bottomAppBarMainState: BottomAppBarMainState,
//            chipMainState: ChipMainState,
//            colorMainState: ColorMainState,
//        ): List<MaterialScreen> =
//            listOf(
//                Text(textMainState),
//                Color(colorMainState),
//                Button(buttonMainState),
//                Chip(chipMainState),
//                BottomAppBar(bottomAppBarMainState),
//            )
//
//        fun design(navController: NavHostController): List<MaterialItem> =
//            listOf(
//                MaterialItem(
//                    image = R.drawable.bottom,
//                    title = "Color",
//                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
//                    goToDetail = { navController.navigate(route = Color.route) },
//                ),
//            )
//
//        fun components(navController: NavHostController): List<MaterialItem> =
//            listOf(
//                MaterialItem(
//                    image = R.drawable.bottom,
//                    title = "App bars: bottom",
//                    description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
//                    goToDetail = { navController.navigate(route = BottomAppBar.route) },
//                ),
//                MaterialItem(
//                    image = R.drawable.appbartop,
//                    title = "App bars: top",
//                    description = "The top app bar displays information and actions relating to the current screen"
//                ),
//                MaterialItem(
//                    image = R.drawable.bottom_navigation,
//                    title = "Bottom navigation",
//                    description = "Bottom navigation bars allow movement between primary destinations in an app"
//                ),
//
//                MaterialItem(
//                    image = R.drawable.buttons,
//                    title = "Buttons",
//                    description = "Buttons allow users to take actions, and make choices, with a single tap "
//                ),
//                MaterialItem(
//                    image = R.drawable.fab,
//                    title = "Buttons: floating action button",
//                    description = "A floating action button (FAB) represents the primary action of a screen "
//                ),
//                MaterialItem(
//                    image = R.drawable.cards,
//                    title = "Cards",
//                    description = "Cards contain content and actions about a single subject"
//                ),
//                MaterialItem(
//                    image = R.drawable.checkboxes,
//                    title = "Checkboxes",
//                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
//                ),
//                MaterialItem(
//                    image = R.drawable.chips,
//                    title = "Chips",
//                    description = "Chips are compact elements that represent an input, attribute, or action ",
//                ),
//                MaterialItem(
//                    image = R.drawable.date_pickers,
//                    title = "Date pickers",
//                    description = "Date pickers let users select a date, or a range of dates.",
//                ),
//                MaterialItem(
//                    image = R.drawable.dialogs,
//                    title = "Dialogs",
//                    description = "Dialogs inform users about a task and can contain critical information, require decisions, or involve multiple tasks.",
//                ),
//                MaterialItem(
//                    image = R.drawable.menus,
//                    title = "Menus",
//                    description = "Menus display a list of choices on temporary surfaces.",
//                ),
//                MaterialItem(
//                    image = R.drawable.navigation_drawer,
//                    title = "Navigation Drawer",
//                    description = "Navigation drawers provide access to destinations in your app.",
//                ),
//                MaterialItem(
//                    image = R.drawable.radio_buttons,
//                    title = "Radio Buttons",
//                    description = "Radio buttons allow users to select one option from a set.",
//                ),
//                MaterialItem(
//                    image = R.drawable.sheets_bottom,
//                    title = "Sheets: bottom",
//                    description = "Bottom sheets are surfaces containing supplementary content that are anchored to the bottom of the screen.",
//                ),
//                MaterialItem(
//                    image = R.drawable.sliders,
//                    title = "Sliders",
//                    description = "Sliders allow users to make selections from a range of values.",
//                ),
//                MaterialItem(
//                    image = R.drawable.snackbars,
//                    title = "Snackbars",
//                    description = "Snackbars provide brief messages about app processes at the bottom of the screen.",
//                ),
//                MaterialItem(
//                    image = R.drawable.switches,
//                    title = "Switches",
//                    description = "Switches toggle the state of a single item on or off.",
//                ),
//                MaterialItem(
//                    image = R.drawable.tabs,
//                    title = "Tabs",
//                    description = "Tabs organize content across different screens, data sets, and other interactions.",
//                ),
//                MaterialItem(
//                    image = R.drawable.text_fields,
//                    title = "Text fields",
//                    description = "Text fields let users enter and edit text.",
//                ),
//                MaterialItem(
//                    image = R.drawable.time_pikers,
//                    title = "Time pickers",
//                    description = "Time pickers help users select and set a specific time.",
//                ),
//                MaterialItem(
//                    image = R.drawable.bottom,
//                    title = "Text",
//                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
//                    goToDetail = { navController.navigate(route = Text.route) },
//                ),
//            )
//
//    }
//
//    @Composable
//    abstract fun screen()
//
//    data class Main(
//        val state: MaterialState,
//    ) : MaterialScreen(route) {
//        companion object : Route {
//            override val route: String = "MaterialMain"
//        }
//
//        @Composable
//        override fun screen() {
//            MaterialMain(state)
//        }
//    }
//
//    data class Text(
//        val state: TextMainState
//    ) : MaterialScreen(route) {
//        companion object : Route {
//            override val route: String = "MaterialText"
//        }
//
//        @Composable
//        override fun screen() =
//            TextMain(state)
//    }
//
//    data class Button(
//        val state: ButtonMainState
//    ) : MaterialScreen(route) {
//        companion object : Route {
//            override val route: String = "MaterialButton"
//        }
//
//        @Composable
//        override fun screen() =
//            ButtonMain(state = state)
//    }
//
//    data class BottomAppBar(
//        val state: BottomAppBarMainState
//    ) : MaterialScreen(route) {
//        companion object : Route {
//            override val route: String = "MaterialBottomAppBar"
//        }
//
//        @Composable
//        override fun screen() {
//            BottomAppBarMain(state = state)
//        }
//    }
//
//    data class Chip(
//        val state: ChipMainState
//    ) : MaterialScreen(route = route) {
//        companion object : Route {
//            override val route: String = "MaterialChip"
//        }
//
//        @Composable
//        override fun screen() =
//            ChipMain(state)
//    }
//
//    data class Color(
//        val state: ColorMainState
//    ) : MaterialScreen(route = route) {
//
//        companion object : Route {
//            override val route: String = "MaterialColor"
//        }
//
//        @Composable
//        override fun screen() =
//            ColorMain(state)
//    }
//}
//
//interface Route {
//    val route: String
//}