package com.acv.composeland.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

interface TabItem {
    val title: String
}

@Composable
fun <A : TabItem> Tabs(
    options: List<A>,
    default: A,
    saver: Saver<A, String>,
    content: @Composable (A) -> Unit
) {
    var state = rememberSaveable(saver = saver) { default }

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
        Crossfade(state) {
            content(it)
        }
    }
}