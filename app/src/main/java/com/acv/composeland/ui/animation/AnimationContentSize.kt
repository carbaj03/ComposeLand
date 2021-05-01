package com.acv.composeland.ui.animation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.acv.composeland.R
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui

data class ContentSizeState(
    val goBack: () -> Unit,
)


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationContentSize(
    navController: NavHostController
) {
    val state = ContentSizeState(
        goBack = { navController.popBackStack() },
    )

    var expand by remember { mutableStateOf(true) }

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "AnimatedVisibility",
                Argument("visible", expand.toString()),
            )
        }
    }

    CodeScaffold(
        title = "Button Color",
        goBack = state.goBack,
        code = code,
        sample = {
            Button(
                modifier = Modifier.animateContentSize(),
                onClick = { expand = !expand },
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_click), contentDescription = "Fab")
                if (expand)
                    Text(text = "Love")
            }
        },
        options = {
        },
    )
}