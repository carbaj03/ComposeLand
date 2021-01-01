package com.acv.composeland.material.color

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

data class ColorMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<ColorMainItem>,
)

data class ColorMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun ColorMain(
    navController: NavHostController
) {
    val state = ColorMainState(
        title = "Colors",
        goBack = { navController.popBackStack() },
        items = listOf(ColorMainItem(name = "sadf", goToDetail = {}))
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