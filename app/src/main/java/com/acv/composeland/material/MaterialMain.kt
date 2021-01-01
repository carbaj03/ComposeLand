package com.acv.composeland.material

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.common.H6
import com.acv.composeland.common.TopBarBack
import com.acv.composeland.common.fakeGridItems
import com.acv.composeland.screen.BottomAppBarScreen
import com.acv.composeland.screen.ColorScreen
import com.acv.composeland.screen.MaterialScreen


data class MaterialState(
    val title: String,
    val goBack: () -> Unit,
    val designMaterialState: DesignMaterialState,
    val componentsMaterialState: ComponentsMaterialState,
)

data class MaterialItem(
    val image: Int,
    val title: String,
    val description: String,
    val goToDetail: () -> Unit
)

@Composable
fun MaterialMain(
    navController: NavHostController
) {
    val state = MaterialState(
        title = "Material",
        goBack = { navController.popBackStack() },
        componentsMaterialState = ComponentsMaterialState(
            listOf(
                MaterialItem(
                    image = R.drawable.bottom,
                    title = "App bars: bottom",
                    description = " A bottom app bar displays navigation and key actions at the bottom of mobile screens ",
                    goToDetail = { navController.navigate(route = BottomAppBarScreen.Main.route) },
                ),
                MaterialItem(
                    image = R.drawable.buttons,
                    title = "Buttons",
                    description = "Buttons allow users to take actions, and make choices, with a single tap ",
                    goToDetail = { navController.navigate(MaterialScreen.Button.route) }
                )
            )
        ),
        designMaterialState = DesignMaterialState(
            listOf(
                MaterialItem(
                    image = R.drawable.snackbars,
                    title = "Color",
                    description = "sdf",
                    goToDetail = { navController.navigate(ColorScreen.Main.route) }
                )
            )
        ),
    )

    Scaffold(
        topBar = {
            TopBarBack(
                title = state.title,
                goBack = { state.goBack() }
            )
        },
    ) {
        Tabs(
            options = listOf("Design", "Components", "Develop"),
        ) {
            when (it) {
                0 -> DesignMaterialMain(state = state.designMaterialState)
                1 -> ComponentsMaterialMain(state = state.componentsMaterialState)
                else -> Text(text = "saa")
            }
        }
    }
}


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
                val vectorAsset = loadImageResource(id = screen.image)
                vectorAsset.resource.resource?.let {
                    Image(
                        bitmap = it,
                        contentScale = ContentScale.Fit,
                    )
                }

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

private fun createTestImage(color: Color): ImageBitmap {
    val imageAsset = ImageBitmap(100, 100)
    Canvas(imageAsset).drawCircle(
        Offset(50.0f, 50.0f), 50.0f,
        Paint().apply { this.color = color }
    )
    return imageAsset
}

data class ComponentsMaterialState(
    val items: List<MaterialItem>,
)


@Composable
fun ComponentsMaterialMain(
    state: ComponentsMaterialState
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

@Composable
fun Tabs(
    options: List<String>,
    content: @Composable (Int) -> Unit
) {
    var state by savedInstanceState { 0 }
    Column {
        TabRow(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary,
            selectedTabIndex = state,
        ) {
            options.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = state == index,
                    onClick = { state = index }
                )
            }
        }
        Crossfade(current = state) {
            content(it)
        }
    }
}