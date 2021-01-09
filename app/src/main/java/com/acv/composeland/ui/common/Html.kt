package com.acv.composeland.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


@Composable
fun H1(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h1)
}

@Composable
fun H2(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h2)
}

@Composable
fun H3(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h3)
}

@Composable
fun H4(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h4)
}

@Composable
fun H5(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h5)
}

@Composable
fun H6(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h6)
}

@Composable
fun Subtitle(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Justify,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.subtitle1,
        color = color,
        maxLines = maxLines,
    )
}

@Composable
fun Body(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Justify,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.body1,
        color = color,
        maxLines = maxLines,

        )
}