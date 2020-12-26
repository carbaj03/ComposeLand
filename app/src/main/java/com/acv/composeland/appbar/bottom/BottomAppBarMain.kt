package com.acv.composeland.appbar.bottom

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acv.composeland.chip.Body
import com.acv.composeland.chip.H2
import com.acv.composeland.chip.H3
import com.acv.composeland.material.RelatedItem
import com.acv.composeland.material.RelatedItems

data class BottomAppBarMainState(
    val title: String,
    val goBack: () -> Unit,
    val description: String,
    val usage: String,
    val usageDescription: String,
    val related: List<RelatedItem>,
    val items: List<BottomAppBarMainItem>,
)

data class BottomAppBarMainItem(
    val name: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun BottomAppBarMain(
    state: BottomAppBarMainState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = {
                        state.goBack()
                    }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        },
    ) {
        ScrollableColumn {
            H2(text = "App bars: bottom")
            Body(text = state.description)
            H3(text = state.usage)
            Body(text = state.usageDescription)

            RelatedItems(items = state.related)

            state.items.forEach { screen ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { screen.goToDetail() }
                ) {
                    Text(text = screen.name)
                }
            }
        }
    }
}