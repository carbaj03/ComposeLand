package com.acv.composeland.button

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


data class ButtonMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<ButtonMainItem>,
)

data class ButtonMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun ButtonMain(
    navController: NavHostController
) {
    val state = ButtonMainState(
        title = "Button Examples",
        goBack = { navController.popBackStack() },
        items = ButtonScreen.buttonItems(navController),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = { state.goBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
    ) {
        ScrollableColumn {
            state.items.forEach { screen ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(onClick = { screen.goToDetail() })
                ) {
                    Text(text = screen.name)
                }
            }
        }
    }

}