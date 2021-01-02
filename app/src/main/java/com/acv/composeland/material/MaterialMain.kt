package com.acv.composeland.material

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.common.TopBarBack
import com.acv.composeland.screen.BottomAppBarScreen
import com.acv.composeland.screen.ColorScreen
import com.acv.composeland.screen.MaterialScreen


data class MaterialState(
    val title: String,
    val goBack: () -> Unit,
    val designMaterialState: DesignMaterialState,
    val componentsMaterialState: ComponentsMaterialState,
)

data class MaterialItem(
    val image: Int,
    val title: String,
    val description: String,
    val goToDetail: () -> Unit
)

@Composable
fun MaterialMain(
    navController: NavHostController
) {
    val state = MaterialState(
        title = "Material",
        goBack = { navController.popBackStack() },
        componentsMaterialState = ComponentsMaterialState(
            listOf(
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "App bars: bottom",
                    description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
                    goToDetail = { navController.navigate(route = BottomAppBarScreen.Main.route) },
                ),
                MaterialItem(
                    image = R.drawable.buttons,
                    title = "Buttons",
                    description = "Buttons allow users to take actions, and make choices, with a single tap ",
                    goToDetail = { navController.navigate(MaterialScreen.Button.route) }
                ),
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "Text",
                    description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                    goToDetail = { navController.navigate(route = MaterialScreen.Text.route) },
                ),
            )
        ),
        designMaterialState = DesignMaterialState(
            listOf(
                MaterialItem(
                    image = R.drawable.snackbars,
                    title = "Color",
                    description = "sdf",
                    goToDetail = { navController.navigate(ColorScreen.Main.route) }
                )
            )
        ),
    )

    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        Tabs(
            options = listOf("Design", "Components", "Develop"),
        ) {
            when (it) {
                0 -> DesignMaterialMain(state = state.designMaterialState)
                1 -> ComponentsMaterialMain(state = state.componentsMaterialState)
                else -> Text(text = "saa")
            }
        }
    }
}


@Composable
fun Tabs(
    options: List<String>,
    content: @Composable (Int) -> Unit
) {
    var state by savedInstanceState { 0 }
    Column {
        TabRow(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            selectedTabIndex = state,
        ) {
            options.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
        Crossfade(current = state) {
            content(it)
        }
    }
}