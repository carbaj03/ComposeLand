package com.acv.composeland.ui.appbar.bottom.implementation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.acv.composeland.ui.appbar.bottom.BottomAppBarMainState
import com.acv.composeland.ui.common.fakeGridItems

@Composable
fun Implementation(
    state: BottomAppBarMainState
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