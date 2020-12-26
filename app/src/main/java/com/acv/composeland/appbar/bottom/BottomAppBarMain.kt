package com.acv.composeland.appbar.bottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.acv.composeland.chip.Body
import com.acv.composeland.chip.H2
import com.acv.composeland.chip.H3


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

data class RelatedItem(
    val action: () -> Unit,
    val title: String,
    val subTitle: String,
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
            Body(text = "A bottom app bar displays navigation and key actions at the bottom of mobile screens.")
            H3(text = "Usage")
            Body(text = "Bottom app bars provide access to a bottom navigation drawer and up to four actions, including the floating action button.")

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

@Composable
fun RelatedItems(items: List<RelatedItem>) {
    Row(modifier = Modifier.padding(8.dp)) {
        items.forEach { item ->
            Card(modifier = Modifier.clickable { item.action() }) {
                Related(item)
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

private fun createTestImage(): ImageBitmap {
    val imageAsset = ImageBitmap(100, 100)
    Canvas(imageAsset).drawCircle(
        Offset(50.0f, 50.0f), 50.0f,
        Paint().apply { this.color = Color.Cyan }
    )
    return imageAsset
}

@Composable
fun Related(
    state: RelatedItem
) {
    ConstraintLayout {
        val (title, description, image) = createRefs()

        Image(
            modifier = Modifier.constrainAs(image) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            },
            bitmap = createTestImage(),
        )

        Text(
            text = state.title,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(image.end)
            },
            fontWeight = FontWeight.Bold
        )

        Text(
            text = state.subTitle,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(image.end)
                top.linkTo(title.bottom)
            }
        )
    }
}