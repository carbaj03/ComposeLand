package com.acv.composeland.datastate

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import com.acv.composeland.common.H6

data class DataAndStateState(
    val title: String,
    val goBack: () -> Unit,
    val example: Example,
)


data class Other(
    val example: Example
)
data class Example(
    val isExpanded: Boolean,
    val onExpand: () -> Unit,
)

@Composable
fun DataAndStateMain(
    state: DataAndStateState
) {

    Example(
        title = state.title,
        goBack = state.goBack,
        example = state.example
    )
}

@Composable
fun Example(
    title: String,
    goBack: () -> Unit,
    example: Example
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { H6(text = title) },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
        floatingActionButton = {
            if (example.isExpanded) {
                FloatingActionButton(onClick = { example.onExpand() }) {
                    Icon(Icons.Filled.Favorite)
                }
            }
        },
    ) {
        Text(text = example.toString())
        Button(onClick = { example.onExpand() }) {
            Text(text = "Button")
        }
    }
}