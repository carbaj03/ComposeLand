package com.acv.composeland.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RelatedItems(
    modifier: Modifier,
    items: List<RelatedItem>
) {
    Row(modifier = modifier.padding(8.dp)) {
        items.forEach { item ->
            Card(modifier = Modifier.clickable { item.action() }
            ) {
                Related(modifier = Modifier.padding(4.dp), state = item)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}