package com.acv.composeland.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.common.fakeGridItems
import com.acv.composeland.screen.ButtonScreen


data class ButtonMainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<ButtonMainItem>,
)

data class ButtonMainItem(
    val name: String,
    val icon : Int,
    val goToDetail: () -> Unit = {}
)

@Composable
fun ButtonMain(
    navController: NavHostController
) {
    val state = ButtonMainState(
        title = "Button Examples",
        goBack = { navController.popBackStack() },
        items = listOf(
            ButtonMainItem(
                icon = R.drawable.ic_palette,
                name = "Color",
                goToDetail = { navController.navigate(ButtonScreen.Color.route) }
            ),
            ButtonMainItem(
                icon = R.drawable.ic_click,
                name = "Click",
                goToDetail = { navController.navigate(ButtonScreen.Click.route) }
            )
        ),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
                navigationIcon = {
                    IconButton(onClick = { state.goBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
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
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { screen.goToDetail() })
                    ) {
                        ConstraintLayout(modifier = Modifier.padding(4.dp).fillMaxSize()) {
                            val (title, description, image) = createRefs()

                            Icon(
                                modifier = Modifier
                                    .constrainAs(image) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                    },
                                imageVector = vectorResource(id = screen.icon)
                            )
                            Text(
                                modifier = Modifier
                                    .constrainAs(title) {
                                        linkTo(image.end, parent.end, bias = 0f)
                                        width = Dimension.preferredWrapContent
                                    },
                                textAlign = TextAlign.Start,
                                text = screen.name,
                            )

                            Text(
                                modifier = Modifier
                                    .constrainAs(description) {
                                        linkTo(image.end, parent.end, bias = 0f)
                                        top.linkTo(title.bottom)
                                        width = Dimension.preferredWrapContent
                                    },
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                text = "sdfsdafdsa ssadf da f dafd",
                            )
                        }
                    }
                }
            }
        }
    }

}