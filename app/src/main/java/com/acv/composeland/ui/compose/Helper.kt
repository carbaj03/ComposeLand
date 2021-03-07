package com.acv.composeland.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import com.acv.composeland.suspend.launch
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@OptIn(InternalCoroutinesApi::class)
@Composable
fun underlineClick(
    text: String,
    action: () -> Unit
): Pair<Modifier, AnnotatedString> {
    val interactionState = remember { MutableInteractionSource() }
    var text by remember { mutableStateOf(buildAnnotatedString { append(text = text) }) }
    val draggable = Modifier.clickable(
        onClick = { action() },
    )
    val scope = rememberCoroutineScope()
    scope.launch {
        interactionState.interactions.collect {
            text = when (it) {
                is PressInteraction.Press -> buildAnnotatedString {
                    pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                    append(text)
                    pop()
                }
                else -> buildAnnotatedString { append(text = text) }
            }
        }
    }

    return draggable to text
}

// How to create a modifier
@Stable
fun Modifier.padding(all: Dp) =
    this then PaddingModifier(start = all, top = all, end = all, bottom = all, rtlAware = true)

// Implementation detail
private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean
) : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val horizontal = start.toPx().toInt() + end.toPx().toInt()
        val vertical = top.toPx().toInt() + bottom.toPx().toInt()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        return layout(width, height) {
            if (rtlAware) {
                placeable.placeRelative(start.toPx().toInt(), top.toPx().toInt())
            } else {
                placeable.place(start.toPx().toInt(), top.toPx().toInt())
            }
        }
    }
}