package com.acv.composeland.appbar.bottom.design

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.common.ExpandableCard

@Composable
fun Design(
    state: DesignState,
) {
    Log.e("design", state.usage.toString())
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

    var behaviourExpanded by savedInstanceState { false }
    ExpandableCard(
        title = state.behaviour.title,
        onExpand = { behaviourExpanded = true },
        isExpanded = behaviourExpanded
    ) {
        Behaviour(state.behaviour)
    }

    Theming(theming = state.theming)

    Specs(specs = state.specs)
}