package com.acv.composeland.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.unit.dp
import com.acv.composeland.common.Body
import com.acv.composeland.common.H6
import com.acv.composeland.common.fakeGridItems


data class MainState(
    val title: String,
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
fun MainScreen(state: MainState) {
    Scaffold(
        topBar = {
            TopBar(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        LazyColumn {
            fakeGridItems(state.items, 2) { screen ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(onClick = { screen.goToDetail() })
                ) {
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


@Composable
private fun TopBar(
    title: String,
    goBack: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {
                goBack()
            }) {
                Icon(Icons.Filled.ArrowBack)
            }
        }
    )
}
