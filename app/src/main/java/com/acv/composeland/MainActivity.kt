package com.acv.composeland

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.acv.composeland.appbar.bottom.BottomAppBarDependencies
import com.acv.composeland.button.ButtonDependencies
import com.acv.composeland.chip.ChipDependencies
import com.acv.composeland.main.MainDependencies
import com.acv.composeland.main.MainItem
import com.acv.composeland.main.Screen
import com.acv.composeland.text.TextDependencies
import com.acv.composeland.ui.ComposeLandTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLandTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    val mainItems = listOf(
        MainItem(
            image = R.drawable.bottom,
            title = "App bars: bottom",
            description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
            goToDetail = { navController.navigate(route = Screen.BottomAppBar.route) },
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "App bars: top",
            description = "The top app bar displays information and actions relating to the current screen"
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Bottom navigation",
            description = "Bottom navigation bars allow movement between primary destinations in an app"
        ),

        MainItem(
            image = R.drawable.bottom,
            title = "Buttons",
            description = "Buttons allow users to take actions, and make choices, with a single tap "
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Buttons: floating action button",
            description = "A floating action button (FAB) represents the primary action of a screen "
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Cards",
            description = "Cards contain content and actions about a single subject"
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Checkboxes",
            description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Chips",
            description = "Chips are compact elements that represent an input, attribute, or action ",
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Checkboxes",
            description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
        ),
        MainItem(
            image = R.drawable.bottom,
            title = "Text",
            description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
            goToDetail = { navController.navigate(route = Screen.Text.route) },
        ),
    )

    Screen.Main(
        navController,
        MainDependencies({ navController.popBackStack() }, mainItems),
        TextDependencies(""),
        ButtonDependencies(""),
        ChipDependencies(""),
        BottomAppBarDependencies("", Screen.Text(TextDependencies(""))),
    ).screen()


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLandTheme {
        App()
    }
}