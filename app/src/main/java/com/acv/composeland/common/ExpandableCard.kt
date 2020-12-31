package com.acv.composeland.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCard(
    title: String,
    description: String? = null,
    onExpand: () -> Unit,
    isExpanded : Boolean,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = { onExpand() })
            .fillMaxWidth()
    ) {
        H4(
            modifier = Modifier.padding(top = 8.dp),
            text = title
        )
        description?.let {
            Body(
                modifier = Modifier.padding(top = 8.dp),
                text = description
            )
        }
        if (isExpanded) {
            content()
        }
    }
}