package com.acv.composeland.ui.theming.codelab

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.acv.composeland.ui.common.TopBarBack


data class ThemingCodelabState(
    val title: String,
    val goBack: () -> Unit,
) {
    companion object {
        fun empty(navigator: ThemingCodelabNavigator) =
            ThemingCodelabState(
                title = "Theming",
                goBack = { navigator.goBack() },
            )
    }
}

@Composable
fun ThemingCodelab(
    navigator: ThemingCodelabNavigator,
) {
    val state = ThemingCodelabState.empty(navigator)
    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        Text(text = "Jetpack Compose Theming")
    }
}
