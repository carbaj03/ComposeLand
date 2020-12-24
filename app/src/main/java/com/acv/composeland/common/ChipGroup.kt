package com.acv.composeland.common

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.chip.Chip
import com.acv.composeland.chip.ChipConstants

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selected: String = "none",
    onChange: (String) -> Unit
) {
    var selected by remember { mutableStateOf(selected) }
    ScrollableRow(modifier = modifier) {
        items.forEach { id ->
            Chip(
                modifier = Modifier.padding(vertical = 8.dp),
                text = id,
                selected = selected == id,
                onSelect = {
                    selected = if (selected == id) "none" else id
                    onChange(id)
                },
                colors = ChipConstants.defaultOutlinedChipColors(
                    selectedBackgroundColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.medium),
                    selectedContentColor = contentColorFor(color = MaterialTheme.colors.primary),
                    unselectedBackgroundColor = MaterialTheme.colors.background,
                    unselectedContentColor = MaterialTheme.colors.primary,
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
