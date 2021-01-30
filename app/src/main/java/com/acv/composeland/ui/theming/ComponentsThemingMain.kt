package com.acv.composeland.ui.theming

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.common.H6
import com.acv.composeland.ui.common.fakeGridItems

data class ComponentsThemingState(
    val items: List<ThemingItem>,
)

@Composable
fun ComponentsThemingMain(
    state: ComponentsThemingState
) {
    LazyColumn {
        fakeGridItems(
            items = state.items,
            columns = 2,
        ) { screen ->
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable(onClick = { screen.goToDetail() })
            ) {
                Image(
                    bitmap = imageResource(id = screen.image),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
                H6(text = screen.title)
                Text(
                    text = screen.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}