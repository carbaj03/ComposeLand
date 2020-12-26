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

data class RelatedItem(
    val action: () -> Unit,
    val title: String,
    val subTitle: String,
)

@Composable
fun RelatedItems(items: List<RelatedItem>) {
    Row(modifier = Modifier.padding(8.dp)) {
        items.forEach { item ->
            Card(modifier = Modifier.clickable { item.action() }) {
                Related(item)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}