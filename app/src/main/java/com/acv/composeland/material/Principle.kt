package com.acv.composeland.material

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.font.FontWeight


data class PrincipleItem(
    val image: Int,
    val title: String,
    val subTitle: String,
)

@Composable
fun Principle(
    state: PrincipleItem
) {
    Column {
        Image(
            bitmap = createTestImage(),
        )

        Text(
            text = state.title,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = state.subTitle,
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