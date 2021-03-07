package com.acv.composeland.ui.material

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acv.composeland.R
import com.acv.composeland.ui.common.TabItem
import com.acv.composeland.ui.common.Tabs
import com.acv.composeland.ui.common.TopBarBack
import com.acv.composeland.ui.theming.ThemingTabItem


data class MaterialState(
    val title: String,
    val goBack: () -> Unit,
    val designMaterialState: DesignMaterialState,
    val componentsMaterialState: ComponentsMaterialState,
) {
    companion object {
        fun empty(materialNavigator: MaterialNavigator) =
            MaterialState(
                title = "Material",
                goBack = { materialNavigator.goBack() },
                componentsMaterialState = ComponentsMaterialState(
                    listOf(
                        MaterialItem(
                            image = R.drawable.bottom,
                            title = "App bars: bottom",
                            description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
                            goToDetail = { },
                        ),
                        MaterialItem(
                            image = R.drawable.buttons,
                            title = "Buttons",
                            description = "Buttons allow users to take actions, and make choices, with a single tap ",
                            goToDetail = { }
                        ),
                        MaterialItem(
                            image = R.drawable.bottom,
                            title = "Text",
                            description = "Checkboxes allow the user to select one or more items from a set or turn an option on or off",
                            goToDetail = { },
                        ),
                    )
                ),
                designMaterialState = DesignMaterialState(
                    listOf(
                        MaterialItem(
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

data class MaterialItem(
    val image: Int,
    val title: String,
    val description: String,
    val goToDetail: () -> Unit
)

@Composable
fun MaterialMain(
    materialNavigator: MaterialNavigator,
    materialViewModel: MaterialViewModel = viewModel(),
) {
    val id = 0
    var state by remember { mutableStateOf(MaterialState.empty(materialNavigator)) }
    LaunchedEffect(id) { state = materialViewModel.init(materialNavigator) }
//    val state by produceState(MaterialState.empty(navController), materialViewModel) {
//        materialViewModel.init(MaterialNavigatorComponent(navController))
//    }
    var stateTabs by rememberSaveable(stateSaver = MaterialTabItem.AutoSaver) { mutableStateOf(MaterialTabItem.Develop) }
    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        Tabs(
            options = MaterialTabItem.items,
            selected = stateTabs,
            onSelection = { stateTabs = it },
        ) {
            when (it) {
                MaterialTabItem.Design -> DesignMaterialMain(state = state.designMaterialState)
                MaterialTabItem.Components -> ComponentsMaterialMain(state = state.componentsMaterialState)
                MaterialTabItem.Develop -> Text(text = "saa")
            }
        }
    }
}


sealed class MaterialTabItem(
    override val title: String,
) : TabItem {
    companion object {
        val items = listOf(Design, Components, Develop)
        val AutoSaver = Saver<TabItem, String>(
            save = { it.title },
            restore = {
                when (it) {
                    "Design" -> Design
                    "Componets" -> Components
                    else -> Develop
                }
            }
        )
    }

    object Design : MaterialTabItem("Design")
    object Components : MaterialTabItem("Components")
    object Develop : MaterialTabItem("Develop")
}


