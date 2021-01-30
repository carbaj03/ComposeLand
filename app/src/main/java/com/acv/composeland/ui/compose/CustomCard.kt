package com.acv.composeland.ui.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(
    type: String,
    title: String,
    description: String,
    imageVector: ImageVector,
    guide: () -> Unit,
    codelab: () -> Unit,
) {
    Log.e("CustomCard", title)
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
                contentDescription = null,
            )
        }
    }
}