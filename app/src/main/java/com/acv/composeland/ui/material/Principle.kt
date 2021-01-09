package com.acv.composeland.ui.material

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight


data class PrincipleItem(
    val image: Int,
    val title: String,
    val subTitle: String,
)

@Composable
fun Principle(
    modifier: Modifier,
    state: PrincipleItem
) {
    Column(modifier = modifier) {
        Image(
            bitmap = imageResource(id = state.image),
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