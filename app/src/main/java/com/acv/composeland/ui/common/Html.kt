package com.acv.composeland.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

//interface HtmlContext {

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
fun Body(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = Color.Unspecified
) {
    Text(color = color, maxLines = maxLines, modifier = modifier, text = text, style = MaterialTheme.typography.body1)
}
//}
//
//object Html : HtmlContext {
//    @Composable
//    inline operator fun invoke(crossinline c: HtmlContext.() -> Unit) {
//        c()
//    }
//}