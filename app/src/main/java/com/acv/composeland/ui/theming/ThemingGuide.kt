package com.acv.composeland.ui.theming

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.ui.viewinterop.viewModel
import com.acv.composeland.R
import com.acv.composeland.ui.common.TabItem
import com.acv.composeland.ui.common.Tabs
import com.acv.composeland.ui.common.TopBarBack
import com.acv.composeland.ui.material.MaterialTabItem


data class ThemingState(
    val title: String,
    val goBack: () -> Unit,
    val designThemingState: DesignThemingState,
    val componentsThemingState: ComponentsThemingState,
) {
    companion object {
        fun empty(themingNavigator: ThemingNavigator) =
            ThemingState(
                title = "Theming",
                goBack = { themingNavigator.goBack() },
                componentsThemingState = ComponentsThemingState(
                    listOf(
                        ThemingItem(
                            image = R.drawable.bottom,
                            title = "App bars: bottom",
                            description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
                            goToDetail = { },
                        ),
                        ThemingItem(
                            image = R.drawable.buttons,
                            title = "Buttons",
                            description = "Buttons allow users to take actions, and make choices, with a single tap ",
                            goToDetail = { }
                        ),
                        ThemingItem(
                            image = R.drawable.bottom,
                            title = "Text",
                            description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                            goToDetail = { },
                        ),
                    )
                ),
                designThemingState = DesignThemingState(
                    listOf(
                        ThemingItem(
                            image = R.drawable.snackbars,
                            title = "Color",
                            description = "sdf",
                            goToDetail = { }
                        )
                    )
                ),
            )
    }
}

data class ThemingItem(
    val image: Int,
    val title: String,
    val description: String,
    val goToDetail: () -> Unit
)

@Composable
fun ThemingGuide(
    themingNavigator: ThemingNavigator,
    themingViewModel: ThemingViewModel = viewModel(),
) {
    val id = 0
    var state by remember { mutableStateOf(ThemingState.empty(themingNavigator)) }
    LaunchedEffect(id) { state = themingViewModel.init(themingNavigator) }

    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        Tabs(
            options = ThemingTabItem.items,
            default = ThemingTabItem.Design,
            saver = ThemingTabItem.AutoSaver,
        ) {
            when (it) {
                ThemingTabItem.Design -> DesignThemingMain(state = state.designThemingState)
                ThemingTabItem.Components -> ComponentsThemingMain(state = state.componentsThemingState)
                ThemingTabItem.Develop -> Text(text = "saa")
            }
        }
    }
}

sealed class ThemingTabItem(
    override val title: String
) : TabItem {
    companion object {
        val items = listOf(Design, Components, Develop)
        val AutoSaver = Saver<TabItem, String>(
            save = { it.title },
            restore = { when(it){
                "Design" -> MaterialTabItem.Design
                "Componets" -> MaterialTabItem.Components
                else -> MaterialTabItem.Develop
            } }
        )
    }

    object Design : ThemingTabItem("Design")
    object Components : ThemingTabItem("Components")
    object Develop : ThemingTabItem("Develop")
}
