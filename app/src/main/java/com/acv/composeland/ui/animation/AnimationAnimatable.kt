package com.acv.composeland.ui.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationAnimatable(
    navController: NavHostController
) {
    var enabled by remember { mutableStateOf(true) }
    val color = remember { Animatable(Color.Gray) }

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "AnimatedVisibility",
                Argument("visible", color.toString()),
            )
        }
    }

    CodeScaffold(
        title = "Animatable",
        goBack = { navController.popBackStack() },
        code = code,
        sample = {
            LaunchedEffect(enabled) {
                color.animateTo(
                    targetValue = if (enabled) Color.Green else Color.Red,
                    animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                )
            }
            Box(
                Modifier
                    .size(250.dp, 250.dp)
                    .background(color.value)
            )
        },
        options = {
            Chip(
                text = "Enabled",
                selected = enabled,
                onSelect = { enabled = it },
            )
        },
    )
}