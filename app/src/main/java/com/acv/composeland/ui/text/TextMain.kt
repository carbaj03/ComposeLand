package com.acv.composeland.ui.text

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.ui.button.Grid
import com.acv.composeland.ui.button.GridItem
import com.acv.composeland.ui.screen.TextScreen

data class TextMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<TextMainItem>,
)

data class TextMainItem(
    override val icon: Int,
    override val title: String,
    override val description: String,
    override val goToDetail: () -> Unit
) : GridItem

@Composable
fun TextMain(
    navController: NavHostController
) {
    val state = TextMainState(
        title = "Text Examples",
        goBack = { navController.popBackStack() },
        items = listOf(
            TextMainItem(
                icon = R.drawable.ic_click,
                title = "Color",
                description = "Colors of the app",
                goToDetail = { navController.navigate(TextScreen.Color.route) }
            )
        )
    )
    Scaffold(
        topBar = { TextToolbar(state.title, state.goBack) },
    ) {
        Grid(items = state.items)
    }
}

@Composable
private fun TextToolbar(title: String, goBack: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(Icons.Filled.ArrowBack)
            }
        }
    )
}