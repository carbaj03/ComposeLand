package com.acv.composeland.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

interface TabItem {
    val title: String
}

@Composable
fun <A : TabItem> Tabs(
    options: List<A>,
    selected: A,
    onSelection: (A) -> Unit,
    content: @Composable (A) -> Unit
) {
    Column {
        TabRow(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            selectedTabIndex = options.indexOf(selected),
        ) {
            options.forEachIndexed { _, tab ->
                Tab(
                    text = { Text(text = tab.title) },
                    selected = selected == tab,
                    onClick = { onSelection(tab) }
                )
            }
        }
        Crossfade(selected) {
            content(it)
        }
    }
}