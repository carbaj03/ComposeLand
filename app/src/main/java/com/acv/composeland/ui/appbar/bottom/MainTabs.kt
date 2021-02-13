package com.acv.composeland.ui.appbar.bottom

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.acv.composeland.ui.appbar.bottom.design.TabItem

@Composable
fun MainTabs(
    items: List<TabItem>,
    content: @Composable (TabItem) -> Unit
) {
    var state by rememberSaveable { mutableStateOf(0) }
    Column {
        ScrollableTabRow(
            selectedTabIndex = state,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
        ) {
            items.forEachIndexed { index, item ->
                FancyTab(
                    title = item.title,
                    icon = item.icon,
                    onClick = { state = index; },
                    selected = (index == state),
                    new = item.new
                )
            }
        }
        content(items[state])
    }
}