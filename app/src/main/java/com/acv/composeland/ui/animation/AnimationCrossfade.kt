package com.acv.composeland.ui.animation

import androidx.compose.animation.Crossfade
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui

enum class Page {
    A, B
}

@Composable
fun AnimationCrossfade(
    navController: NavHostController
) {
    val state = ContentSizeState(
        goBack = { navController.popBackStack() },
    )

    var page: Page by remember { mutableStateOf(Page.A) }

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "Crossfade",
                Argument("targetState", page.toString()),
            )
        }
    }

    CodeScaffold(
        title = "Button Color",
        goBack = state.goBack,
        code = code,
        sample = {
            Crossfade(targetState = page) { screen ->
                when (screen) {
                    Page.A -> Text("Page A")
                    Page.B -> Text("Page B")
                }
            }
        },
        options = {
            Button(onClick = {
                page = when (page) {
                    Page.A -> Page.B
                    Page.B -> Page.A
                }
            }) {
                Text("Page ${page.name}")
            }
        },
    )
}