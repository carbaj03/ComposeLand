package com.acv.composeland.ui.appbar.bottom.design

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.common.ExpandableCard

@Composable
fun Design(
    state: DesignState,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState())
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