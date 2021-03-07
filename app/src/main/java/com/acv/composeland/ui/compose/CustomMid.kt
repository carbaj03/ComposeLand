package com.acv.composeland.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun CustomMid(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    painter: Painter,
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
                painter = painter,
                contentScale = ContentScale.Fit,
                contentDescription = null,
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