package com.acv.composeland.animation

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

data class AnimationMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<AnimationMainItem>,
)

data class AnimationMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun AnimationMain(
    navController: NavHostController
) {
    val state = AnimationMainState(
        title = "Animations",
        goBack = { navController.popBackStack() },
        items = listOf(
            AnimationMainItem("", {})
        )
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