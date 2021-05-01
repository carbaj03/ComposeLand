package com.acv.composeland.ui.animation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationTargetBased(
    navController: NavHostController
) {
    var enabled by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(200),
            typeConverter = Float.VectorConverter,
            initialValue = 200f,
            targetValue = 1000f
        )
    }
    var playTime by remember { mutableStateOf(0L) }
    var animationValue by remember { mutableStateOf(0f) }

    scope.launch {
        val startTime = withFrameNanos { it }
        do {
            playTime = withFrameNanos { it } - startTime
            animationValue = anim.getValueFromNanos(playTime)
        } while (enabled)
    }


    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "AnimatedVisibility",
                Argument("visible", enabled.toString()),
            )
        }
    }

    CodeScaffold(
        title = "Update transition",
        goBack = { navController.popBackStack() },
        code = code,
        sample = {
            Box(
                Modifier
                    .size(250.dp)
                    .offset { IntOffset(animationValue.toInt(), animationValue.toInt()) }
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