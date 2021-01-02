package com.acv.composeland.appbar.bottom

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import com.acv.composeland.appbar.bottom.design.TabItem

@Composable
fun MainTabs(
    items: List<TabItem>,
    content: @Composable (TabItem) -> Unit
) {
    var state by savedInstanceState { 0 }
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