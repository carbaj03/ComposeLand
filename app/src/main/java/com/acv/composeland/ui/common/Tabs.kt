package com.acv.composeland.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.setValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState

interface TabItem {
    val title: String
}

@Composable
fun <A : TabItem> Tabs(
    options: List<A>,
    default: A,
    saver : Saver<A, String>,
    content: @Composable (A) -> Unit
) {
    var state by savedInstanceState(saver = saver) { default }

    Column {
        TabRow(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            selectedTabIndex = options.indexOf(state),
        ) {
            options.forEachIndexed { _, tab ->
                Tab(
                    text = { Text(text = tab.title) },
                    selected = state == tab,
                    onClick = { state = tab }
                )
            }
        }
        Crossfade(current = state) {
            content(it)
        }
    }
}