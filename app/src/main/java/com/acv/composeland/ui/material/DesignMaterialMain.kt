package com.acv.composeland.ui.material

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.common.H6
import com.acv.composeland.ui.common.fakeGridItems


data class DesignMaterialState(
    val items: List<MaterialItem>,
)


@Composable
fun DesignMaterialMain(state: DesignMaterialState) {
    LazyColumn {
        fakeGridItems(state.items, 2) { screen ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable(onClick = { screen.goToDetail() })
            ) {
                Image(
                    painter = painterResource(id = screen.image),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )

                H6(text = screen.title)
                Text(
                    text = screen.description,
                    maxLines = 3,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}