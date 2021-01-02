package com.acv.composeland.appbar.bottom.design

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.common.ExpandableCard

@Composable
fun Design(
    state: DesignState,
) {
    ScrollableColumn(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        ExpandableCard(
            title = state.usage.title,
            description = state.usage.description,
            onExpand = { state.usage.onExpand() },
            isExpanded = state.usage.isExpanded
        ) {
            Usage(state.usage)
        }

        Divider(
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ExpandableCard(
            title = state.behaviour.title,
            onExpand = { state.behaviour.onExpand() },
            isExpanded = state.behaviour.isExpanded
        ) {
            Behaviour(state.behaviour)
        }

        Theming(theming = state.theming)

        Specs(specs = state.specs)
    }
}