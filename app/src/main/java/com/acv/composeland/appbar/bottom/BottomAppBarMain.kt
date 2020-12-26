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
import com.acv.composeland.common.Body
import com.acv.composeland.common.H5
import com.acv.composeland.common.H6
import com.acv.composeland.material.*

data class BottomAppBarMainState(
    val title: String,
    val goBack: () -> Unit,
    val description: String,
    val usage: String,
    val usageDescription: String,
    val related: List<RelatedItem>,
    val principle: String,
    val principles: List<PrincipleItem>,
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
        ScrollableColumn(
            contentPadding = it,
            modifier = Modifier.padding(8.dp)
        ) {
            H5(
                modifier = Modifier.padding(top = 8.dp),
                text = "App bars: bottom"
            )
            Body(
                modifier = Modifier.padding(top = 8.dp),
                text = state.description
            )

            H6(
                modifier = Modifier.padding(top = 8.dp),
                text = state.usage
            )
            Body(
                modifier = Modifier.padding(top = 8.dp),
                text = state.usageDescription
            )
            RelatedItems(
                modifier = Modifier.padding(top = 8.dp),
                items = state.related
            )

            H6(text = state.principle)
            Principles(items = state.principles)

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