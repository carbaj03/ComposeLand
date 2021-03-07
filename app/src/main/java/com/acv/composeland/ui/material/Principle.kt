package com.acv.composeland.ui.material

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight


data class PrincipleItem(
    @DrawableRes val image: Int,
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
            painter = painterResource(id = state.image),
            contentDescription = null
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