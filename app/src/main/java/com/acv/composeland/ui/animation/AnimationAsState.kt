package com.acv.composeland.ui.animation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui

data class AnimationAsStateState(
    val goBack: () -> Unit,
)

data class Border(val width: Dp, val height: Dp)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationAsState(
    navController: NavHostController
) {
    val state = AnimationAsStateState(
        goBack = { navController.popBackStack() },
    )

    var enabled by remember { mutableStateOf(true) }

    val alpha by animateFloatAsState(if (enabled) 1f else 0.5f)
    val size by animateDpAsState(if (enabled) 100.dp else 50.dp)
    val color by animateColorAsState(if (enabled) Color.Red else Color.Blue)
    val myBorder = remember(enabled) {
        if (enabled) {
            Border(1.dp, 1.dp)
        } else {
            Border(1.dp, 1.dp)
        }
    }
    val custom by animateValueAsState(
        myBorder,
        TwoWayConverter<Border, AnimationVector2D>(
            convertToVector = { AnimationVector2D(it.width.value, it.height.value) },
            convertFromVector = { Border(it.v1.dp, it.v2.dp) }
        )
    )
    val intOffset by animateIntOffsetAsState(
        if (enabled) IntOffset(0, 0) else IntOffset(250, 250)
    )
    val offset by animateOffsetAsState(
        if (enabled) Offset(0f, 0f) else Offset(250f, 250f)
    )
    val rect by animateRectAsState(targetValue = Rect(offset, Size(50f, 50f)))

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
        title = "Button Color",
        goBack = state.goBack,
        code = code,
        sample = {
            Box(
                Modifier
                    .size(size)
                    .offset { intOffset }
                    .graphicsLayer(alpha = alpha)
                    .background(color)
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