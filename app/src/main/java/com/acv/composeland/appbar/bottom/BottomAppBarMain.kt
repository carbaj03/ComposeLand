package com.acv.composeland.appbar.bottom

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import com.acv.composeland.appbar.bottom.design.Design
import com.acv.composeland.appbar.bottom.design.DesignState
import com.acv.composeland.appbar.bottom.design.DesignTabItem
import com.acv.composeland.appbar.bottom.design.ImplementationTabItem
import com.acv.composeland.appbar.bottom.implementation.Implementation


data class BottomAppBarMainState(
    val title: String,
    val goBack: () -> Unit,
    val description: String,
    val design: DesignState,
    val items: List<BottomAppBarMainItem>,
    val fabVisible: Boolean,
    val onFabClick: () -> Unit,
)

data class BottomAppBarMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun BottomAppBarMain(
    state: BottomAppBarMainState
) {
    Scaffold(
        topBar = {
            BottomAppBatTopBar(state.title, state.goBack)
        },
        floatingActionButton = {
            if (state.fabVisible) {
                FloatingActionButton(onClick = { state.onFabClick() }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
    ) {
        MainTabs(
            items = listOf(DesignTabItem, ImplementationTabItem),
        ) { item ->
            when (item) {
                DesignTabItem -> Design(state = state.design)
                ImplementationTabItem -> Implementation(state)
            }
        }
    }
}

@Composable
private fun BottomAppBatTopBar(
    title: String,
    goBack: () -> Unit,
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(Icons.Filled.ArrowBack)
            }
        }
    )
}