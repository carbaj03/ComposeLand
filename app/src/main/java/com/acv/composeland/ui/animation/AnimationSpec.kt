package com.acv.composeland.ui.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.ui.chip.Chip
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui
import kotlin.math.roundToInt

enum class Easing {
    FastOutSlowInEasing, LinearOutSlowInEasing, FastOutLinearInEasing, LinearEasing
}

enum class Type {
    Tween, Spring,
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSpec(
    navController: NavHostController
) {
    var enabled by remember { mutableStateOf(true) }
    val color = remember { Animatable(Color.Gray) }
    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }

    val easingState: Map<String, Easing> = Easing.values().map { it.name to it }.toMap()
    val typeState: Map<String, Type> = Type.values().map { it.name to it }.toMap()

    var easing by remember { mutableStateOf(Easing.FastOutLinearInEasing) }
    var type by remember { mutableStateOf(Type.Tween) }

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
    var width by remember { mutableStateOf(0) }

    CodeScaffold(
        modifier = Modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                width = constraints.maxWidth
                layout(placeable.width, placeable.height) {
                    placeable.place(0, 0)
                }
            },
//            .wrapContentWidth(align = Alignment.Horizontal { size, space, layoutDirection ->
//                /* TODO: return a value based on the animation */
//                width = size
//                size
//            })
        title = "Animatable",
        goBack = { navController.popBackStack() },
        code = code,
        sample = {
//            BoxWithConstraints(Modifier.fillMaxSize()) {
            Button(onClick = { /*TODO*/ }) {

            }
            LaunchedEffect(enabled) {
                color.animateTo(
                    targetValue = if (enabled) Color.Green else Color.Red,
                    animationSpec = type.toCompose(easing),
                )
                offset.animateTo(
                    targetValue = if (enabled) Offset(0f, 0f) else Offset(width.toFloat() - 50.dp.value, 0f),
                    animationSpec = type.toCompose(easing),
                )
            }
            Circle(modifier = Modifier.offset { offset.value.toIntOffset() })
//            }
        },
        options = {
            Chip(
                text = "Enabled",
                selected = enabled,
                onSelect = { enabled = it },
            )
            ChipGroup(
                items = easingState.keys.toList(),
                onChange = { easing = Easing.valueOf(it) }
            )
            ChipGroup(
                items = typeState.keys.toList(),
                onChange = { type = Type.valueOf(it) }
            )
        },
    )
}

private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())

@Composable
private fun Circle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(Color.Gray, CircleShape)
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun Easing.toCompose(): androidx.compose.animation.core.Easing =
    when (this) {
        Easing.FastOutSlowInEasing -> FastOutSlowInEasing
        Easing.LinearOutSlowInEasing -> LinearOutSlowInEasing
        Easing.FastOutLinearInEasing -> FastOutLinearInEasing
        Easing.LinearEasing -> LinearEasing
    }

@OptIn(ExperimentalAnimationApi::class)
fun <T> Type.toCompose(easing: Easing): AnimationSpec<T> =
    when (this) {
        Type.Tween -> tween(durationMillis = 1000, easing = easing.toCompose())
        Type.Spring -> spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    }