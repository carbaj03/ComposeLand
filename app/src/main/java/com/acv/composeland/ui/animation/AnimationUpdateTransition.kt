package com.acv.composeland.ui.animation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui

object AnimatingBox {
    enum class BoxState { Collapsed, Expanded }

    @Composable
    operator fun invoke(boxState: BoxState) {
        val transitionData = updateTransitionData(boxState)
        Box(
            modifier = Modifier
                .background(transitionData.color)
                .border(transitionData.borderWidth, Color.Black, RoundedCornerShape(transitionData.borderWidth))
                .size(transitionData.size)
        )
    }

    // Holds the animation values.
    private class TransitionData(
        color: State<Color>,
        size: State<Dp>,
        borderWidth: State<Dp>,
    ) {
        val color by color
        val size by size
        val borderWidth by borderWidth
    }

    // Create a Transition and return its animation values.
    @Composable
    private fun updateTransitionData(boxState: BoxState): TransitionData {
        val transition = updateTransition(boxState)
        val rect by transition.animateRect { state ->
            when (state) {
                BoxState.Collapsed -> Rect(0f, 0f, 100f, 100f)
                BoxState.Expanded -> Rect(100f, 100f, 300f, 300f)
            }
        }

        val borderWidth = transition.animateDp { state ->
            when (state) {
                BoxState.Collapsed -> 5.dp
                BoxState.Expanded -> 0.dp
            }
        }

        val color = transition.animateColor(
            transitionSpec = {
                when {
                    BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                        spring(stiffness = 50f)
                    else ->
                        tween(durationMillis = 500)
                }
            }
        ) { state ->
            when (state) {
                BoxState.Collapsed -> MaterialTheme.colors.primary
                BoxState.Expanded -> Color.Gray
            }
        }

        val size = transition.animateDp { state ->
            when (state) {
                BoxState.Collapsed -> 64.dp
                BoxState.Expanded -> 128.dp
            }
        }
        return remember(transition) { TransitionData(color, size, borderWidth) }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationUpdateTransition(
    navController: NavHostController
) {
    var enabled by remember { mutableStateOf(AnimatingBox.BoxState.Collapsed) }

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
            AnimatingBox(boxState = enabled)
        },
        options = {
            Chip(
                text = "Enabled",
                selected = enabled == AnimatingBox.BoxState.Collapsed,
                onSelect = { enabled = if (it) AnimatingBox.BoxState.Collapsed else AnimatingBox.BoxState.Expanded },
            )
        },
    )
}