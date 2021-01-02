package com.acv.composeland.ui.material.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.common.Body
import com.acv.composeland.ui.common.TopBarBack
import com.acv.composeland.ui.common.fakeGridItems


data class PaletteColorState(
    val title: String,
    val goBack: () -> Unit,
)

@OptIn(ExperimentalLayout::class)
@Composable
fun PaletteColor(state: PaletteColorState) {
    val items = listOf(
        "primary" to MaterialTheme.colors.primary,
        "primary variant" to MaterialTheme.colors.primaryVariant,
        "secondary" to MaterialTheme.colors.secondary,
        "background" to MaterialTheme.colors.background,
        "surface" to MaterialTheme.colors.surface,
        "error" to MaterialTheme.colors.error,
        "on Background" to MaterialTheme.colors.onBackground,
        "on Surface" to MaterialTheme.colors.onSurface,
        "on error" to MaterialTheme.colors.onError,
        "on secondary" to MaterialTheme.colors.onSecondary,
    )

    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        LazyColumn {
            fakeGridItems(items, 2) { screen ->
                Column(
                    modifier = Modifier
                        .background(screen.second)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Body(color = contentColorFor(color = screen.second), text = screen.first)
                }
            }
        }
    }
}