package com.acv.composeland.common

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.chip.Chip
import com.acv.composeland.chip.ChipColors
import com.acv.composeland.chip.ChipConstants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selected: String = "none",
    onChange: (String) -> Unit,
    colors: ChipColors = ChipConstants.defaultOutlinedChipColors(
        selectedBackgroundColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.medium),
        selectedContentColor = contentColorFor(color = MaterialTheme.colors.primary),
        unselectedBackgroundColor = MaterialTheme.colors.background,
        unselectedContentColor = MaterialTheme.colors.primary,
    )
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
                colors = colors
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

//    Row(modifier = Modifier.padding(8.dp)) {
//        Chip(
//            text = "Red",
//            selected = selected == 0,
//            onSelect = {
//                color = if (it) a[1] else a[0]
//                selected = if (selected == 0) -1 else 0
//            },
//            colors = ChipConstants.defaultOutlinedChipColors(
//                selectedBackgroundColor = a[1].first.copy(alpha = ContentAlpha.medium),
//                unselectedContentColor = a[1].first,
//                selectedContentColor = Color.White,
//            )
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        Chip(
//            text = "Yellow",
//            selected = selected == 1,
//            onSelect = {
//                color = if (it) a[2] else a[0]
//                selected = if (selected == 1) -1 else 1
//            },
//            colors = ChipConstants.defaultOutlinedChipColors(
//                selectedBackgroundColor = a[2].first.copy(alpha = ContentAlpha.medium),
//                unselectedContentColor = Color.Black,
//                selectedContentColor = Color.Black,
//            )
//        )
//    }

}
