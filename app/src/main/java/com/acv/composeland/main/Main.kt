package com.acv.composeland.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.acv.composeland.chip.Html


data class MainDependencies(
    val goBack: () -> Unit,
    val items: List<MainItem>,
)

data class MainItem(
    val image: Int,
    val title: String,
    val description: String,
    val goToDetail: () -> Unit = {}
)

@Composable
fun MainScreen(mainDependencies: MainDependencies) {
    Scaffold(
        topBar = { TopBar { mainDependencies.goBack() } },
    ) {
        LazyColumn {
            fakeGridItems(mainDependencies.items, 2) { screen ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(onClick = { screen.goToDetail() })
                ) {
                    Html {
                        val vectorAsset = loadImageResource(screen.image)
                        vectorAsset.resource.resource?.let {
                            Image(
                                bitmap = it,
                                contentScale = ContentScale.Fit,
                            )
                        }

                        H6(text = screen.title)
                        Body(text = screen.description)
                    }
                }
            }
        }
    }
}


@Composable
private fun TopBar(goBack: () -> Unit) {
    TopAppBar(
        title = { Text("Text Examples") },
        navigationIcon = {
            IconButton(onClick = {
                goBack()
            }) {
                Icon(Icons.Filled.ArrowBack)
            }
        }
    )
}

fun <T> LazyListScope.fakeGridItems(
    items: List<T>,
    columns: Int,
    contentPadding: PaddingValues = PaddingValues(),
    horizontalItemPadding: Dp = 0.dp,
    verticalItemPadding: Dp = 0.dp,
    itemContent: @Composable (T) -> Unit
) {
    val rows = when {
        items.size % columns == 0 -> items.size / columns
        else -> (items.size / columns) + 1
    }

    for (row in 0 until rows) {
        if (row == 0) spacerItem(contentPadding.top)

        item {
            Row(
                Modifier.fillMaxWidth()
                    .padding(start = contentPadding.start, end = contentPadding.end)
            ) {
                for (column in 0 until columns) {
                    Box(modifier = Modifier.weight(1f)) {
                        val index = (row * columns) + column
                        if (index < items.size) {
                            itemContent(items[index])
                        }
                    }
                    if (column < columns - 1) {
                        Spacer(modifier = Modifier.preferredWidth(horizontalItemPadding))
                    }
                }
            }
        }

        if (row < rows - 1) {
            spacerItem(verticalItemPadding)
        } else {
            spacerItem(contentPadding.bottom)
        }
    }
}

fun LazyListScope.spacerItem(height: Dp) {
    item {
        Spacer(Modifier.preferredHeight(height).fillParentMaxWidth())
    }
}