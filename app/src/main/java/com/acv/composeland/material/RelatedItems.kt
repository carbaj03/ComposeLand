package com.acv.composeland.material

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RelatedItems(
    modifier: Modifier,
    items: List<RelatedItem>
) {
    Column(modifier = modifier) {
        items.forEach { item ->
            Card(modifier = Modifier
                .fillMaxWidth()
//                .weight(1f)
                .clickable { item.action() }
            ) {
                Related(
                    modifier = Modifier.padding(8.dp),
                    state = item,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}