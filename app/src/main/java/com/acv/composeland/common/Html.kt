package com.acv.composeland.chip

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

//interface HtmlContext {

    @Composable
    fun H1(text: String) {
        Text(text = text, style = MaterialTheme.typography.h1)
    }

    @Composable
    fun H2(text: String) {
        Text(text = text, style = MaterialTheme.typography.h2)
    }

    @Composable
    fun H3(text: String) {
        Text(text = text, style = MaterialTheme.typography.h3)
    }

    @Composable
    fun H6(text: String) {
        Text(text = text, style = MaterialTheme.typography.h6)
    }

    @Composable
    fun Body(text: String) {
        Text(text = text, style = MaterialTheme.typography.body1)
    }
//}
//
//object Html : HtmlContext {
//    @Composable
//    inline operator fun invoke(crossinline c: HtmlContext.() -> Unit) {
//        c()
//    }
//}