package com.acv.composeland.material

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Principles(items: List<PrincipleItem>) {
    Row(modifier = Modifier.padding(8.dp)) {
        items.forEach { item ->
            Principle(item)
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}