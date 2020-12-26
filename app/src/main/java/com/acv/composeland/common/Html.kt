package com.acv.composeland.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//interface HtmlContext {

@Composable
fun H1(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h1)
}

@Composable
fun H2(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h2)
}

@Composable
fun H3(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h3)
}

@Composable
fun H4(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h4)
}


@Composable
fun H5(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h5)
}

@Composable
fun H6(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.h6)
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.body1)
}
//}
//
//object Html : HtmlContext {
//    @Composable
//    inline operator fun invoke(crossinline c: HtmlContext.() -> Unit) {
//        c()
//    }
//}