package com.acv.composeland.ui.datastate

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.H6

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
    navController: NavHostController
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var example by remember(expanded) {
        Log.e("example", expanded.toString())
        mutableStateOf(
            Example(
                isExpanded = expanded,
                onExpand = { expanded = !expanded }
            )
        )
    }
    var state by remember(example) {
        mutableStateOf(
            DataAndStateState(
                title = "Core",
                goBack = { navController.popBackStack() },
                example = example
            )
        )
    }

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
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
        floatingActionButton = {
            if (example.isExpanded) {
                FloatingActionButton(onClick = { example.onExpand() }) {
                    Icon(Icons.Filled.Favorite, null)
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