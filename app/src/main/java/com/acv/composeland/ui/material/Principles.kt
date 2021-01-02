package com.acv.composeland.ui.material

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Principles(
    items: List<PrincipleItem>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        items.forEach { item ->
            Principle(
                modifier = Modifier.weight(1f),
                state = item
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}