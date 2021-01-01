package com.acv.composeland.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.acv.composeland.R
import com.acv.composeland.screen.MainScreen
import com.acv.composeland.screen.MaterialScreen


data class MainState(
    val title: String,
    val goBack: () -> Unit,
    val items: List<MainItem>,
)

sealed class MainItem

data class HeaderMainItem(
    val image: Int,
    val title: String,
    val description: String,
    val guide: () -> Unit,
    val codelab: () -> Unit,
) : MainItem()

data class MidMainItem(
    val image: Int,
    val title: String,
    val description: String,
    val guide: () -> Unit,
    val codelab: () -> Unit,
) : MainItem()

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val state = MainState(
        title = "ComposeLand",
        goBack = { navController.popBackStack() },
        items = listOf(
            HeaderMainItem(
                image = R.drawable.ic_core_cocepts,
                title = "Core",
                description = "Core description",
                guide = { navController.navigate(MainScreen.Core.route) },
                codelab = {},
            ),
            MidMainItem(
                image = R.drawable.ic_compose,
                title = "Material",
                description = "Material description",
                guide = { navController.navigate(MaterialScreen.Main.route) },
                codelab = {},
            )
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.title) },
            )
        },
    ) {
        ScrollableColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            state.items.forEach { item ->
                Spacer(modifier = Modifier.height(8.dp))
                when (item) {
                    is HeaderMainItem -> {
                        CustomCard(
                            imageVector = vectorResource(id = item.image),
                            title = item.title,
                            description = item.description,
                            type = "Featured",
                            guide = item.guide,
                            codelab = item.codelab,
                        )
                    }
                    is MidMainItem -> {
                        customMid(
                            imageVector = vectorResource(id = item.image),
                            title = item.title,
                            description = item.description,
                            guide = item.guide,
                            codelab = item.codelab
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
private fun customMid(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageVector: ImageVector,
    guide: () -> Unit,
    codelab: () -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = 0.dp
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            val (titleRef, imageRef, descriptionRef, codelabRef, guideRef) = createRefs()

            Image(
                modifier = Modifier
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        height = Dimension.value(45.dp)
                    },
                imageVector = imageVector,
                contentScale = ContentScale.Fit,
            )

            Text(
                text = title,
                modifier = Modifier.constrainAs(titleRef) {
                    start.linkTo(parent.start)
                    top.linkTo(imageRef.bottom)
                },
                style = MaterialTheme.typography.h6,
            )

            Text(
                text = description,
                modifier = Modifier.constrainAs(descriptionRef) {
                    start.linkTo(parent.start)
                    top.linkTo(titleRef.bottom, 4.dp)
                },
                style = MaterialTheme.typography.body1,
            )

            val (draggable, text) = underlineClick("Guide") { guide() }
            Text(
                text = text,
                modifier = Modifier
                    .constrainAs(guideRef) {
                        start.linkTo(parent.start)
                        top.linkTo(descriptionRef.bottom, 8.dp)
                    }.then(draggable),
                style = MaterialTheme.typography.button,
            )

            val (codelabAction, codelabTitle) = underlineClick("Codelab") { codelab() }
            Text(
                text = codelabTitle,
                modifier = Modifier.constrainAs(codelabRef) {
                    start.linkTo(parent.start)
                    top.linkTo(guideRef.bottom)
                }.then(codelabAction),
                style = MaterialTheme.typography.button,
            )
        }
    }
}

@Composable
private fun CustomCard(
    type: String,
    title: String,
    description: String,
    imageVector: ImageVector,
    guide: () -> Unit,
    codelab: () -> Unit,
) {
    Card(
        elevation = 0.dp
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth().background(Color.LightGray)
        ) {
            val (typeRef, titleRef, imageRef, descriptionRef, codelabRef, guideRef) = createRefs()

            Text(
                text = type,
                modifier = Modifier.constrainAs(typeRef) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                },
                style = MaterialTheme.typography.caption,
            )

            Text(
                text = title,
                modifier = Modifier.constrainAs(titleRef) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(typeRef.bottom)
                },
                style = MaterialTheme.typography.h5,
            )

            Text(
                text = description,
                modifier = Modifier.constrainAs(descriptionRef) {
                    linkTo(start = parent.start, end = imageRef.start, bias = 0f, startMargin = 8.dp)
                    top.linkTo(titleRef.bottom, 8.dp)
                },
                style = MaterialTheme.typography.body1,
            )

            val (draggable, text) = underlineClick("Guide", guide)
            Text(
                text = text,
                modifier = Modifier
                    .constrainAs(guideRef) {
                        start.linkTo(parent.start, 8.dp)
                        top.linkTo(descriptionRef.bottom, 4.dp)
                    }.then(draggable),
                style = MaterialTheme.typography.button,
            )

            val (actionCodelab, textCodelab) = underlineClick("Codelab", codelab)
            Text(
                text = textCodelab,
                modifier = Modifier.constrainAs(codelabRef) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(guideRef.bottom, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                }.then(actionCodelab),
                style = MaterialTheme.typography.button,
            )

            Image(
                modifier = Modifier
                    .constrainAs(imageRef) {
                        linkTo(top = typeRef.top, bottom = codelabRef.bottom)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                imageVector = imageVector,
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Composable
private fun underlineClick(
    text: String,
    action: () -> Unit
): Pair<Modifier, AnnotatedString> {
    val interactionState = remember { InteractionState() }

    val draggable = Modifier.clickable(
        onClick = { action() },
        interactionState = interactionState
    )

    val newText = when {
        Interaction.Pressed in interactionState -> buildAnnotatedString {
            pushStyle(SpanStyle(textDecoration = TextDecoration.Underline));
            append(text);
            pop()
        }
        else -> buildAnnotatedString { append(text = text) }
    }
    return draggable to newText
}

// How to create a modifier
@Stable
fun Modifier.padding(all: Dp) =
    this then PaddingModifier(start = all, top = all, end = all, bottom = all, rtlAware = true)

// Implementation detail
private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean
) : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val horizontal = start.toIntPx() + end.toIntPx()
        val vertical = top.toIntPx() + bottom.toIntPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        return layout(width, height) {
            if (rtlAware) {
                placeable.placeRelative(start.toIntPx(), top.toIntPx())
            } else {
                placeable.place(start.toIntPx(), top.toIntPx())
            }
        }
    }
}