package com.acv.composeland.material

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontWeight
import com.acv.composeland.appbar.bottom.RelatedItem

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

private fun createTestImage(): ImageBitmap {
    val imageAsset = ImageBitmap(100, 100)
    Canvas(imageAsset).drawCircle(
        Offset(50.0f, 50.0f), 50.0f,
        Paint().apply { this.color = Color.Cyan }
    )
    return imageAsset
}