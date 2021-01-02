package com.acv.composeland.ui.chip

import androidx.compose.animation.AnimatedValueModel
import androidx.compose.animation.VectorConverter
import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedValue
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
//    onClick: () -> Unit = {},
    enabled: Boolean = true,
    interactionState: InteractionState = remember { InteractionState() },
    elevation: ChipElevation? = ChipConstants.defaultElevation(),
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
    border: BorderStroke = ChipConstants.defaultOutlinedBorder,
    colors: ChipColors = ChipConstants.defaultOutlinedChipColors(),
    contentPadding: PaddingValues = ChipConstants.DefaultContentPadding,
    selected: Boolean = false,
    onSelect: (Boolean) -> Unit = {},
    left: (@Composable (Modifier) -> Unit)? = null,
    right: (@Composable (Modifier) -> Unit)? = null,
) {
    // TODO(aelias): Avoid manually putting the clickable above the clip and
    // the ripple below the clip once http://b/157687898 is fixed and we have
    // more flexibility to move the clickable modifier (see candidate approach
    // aosp/1361921)
    val contentColor = colors.contentColor(enabled, selected)
    Surface(
        shape = shape,
        color = colors.backgroundColor(enabled, selected),
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        elevation = elevation?.elevation(enabled, interactionState) ?: 0.dp,
        modifier = modifier.selectable(
            onClick = { onSelect(!selected) },
            enabled = enabled,
            interactionState = interactionState,
            indication = null,
            selected = selected
        )
    ) {
        Providers(AmbientContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(
                value = MaterialTheme.typography.button
            ) {
                Row(
                    Modifier
                        .defaultMinSizeConstraints(
                            minWidth = ChipConstants.DefaultMinWidth,
                            minHeight = ChipConstants.DefaultMinHeight
                        )
                        .indication(interactionState, AmbientIndication.current()),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    left?.let {
                        it(Modifier.padding(start = 4.dp, end = 8.dp).width(24.dp))
                    } ?: Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = text,
                    )
                    right?.let {
                        it(Modifier.padding(start = 4.dp, end = 8.dp).width(24.dp))
                    } ?: Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}

/**
 * Represents the elevation for a button in different states.
 *
 * See [ButtonConstants.defaultElevation] for the default elevation used in a [Button].
 */
@ExperimentalMaterialApi
@Stable
interface ChipElevation {
    /**
     * Represents the elevation used in a button, depending on [enabled] and [interactionState].
     *
     * @param enabled whether the button is enabled
     * @param interactionState the [InteractionState] for this button
     */
    fun elevation(enabled: Boolean, interactionState: InteractionState): Dp
}

object ChipConstants {
    private val HorizontalPadding = 16.dp
    private val VerticalPadding = 8.dp

    /**
     * The default min width applied for the [Button].
     * Note that you can override it by applying Modifier.widthIn directly on [Button].
     */
    val DefaultMinWidth = 24.dp

    /**
     * The default min width applied for the [Button].
     * Note that you can override it by applying Modifier.heightIn directly on [Button].
     */
    val DefaultMinHeight = 36.dp

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun defaultElevation(
        defaultElevation: Dp = 0.dp,
        pressedElevation: Dp = 8.dp,
        disabledElevation: Dp = 0.dp
    ): ChipElevation {
        val clock = AmbientAnimationClock.current.asDisposableClock()
        return remember(defaultElevation, pressedElevation, disabledElevation, clock) {
            DefaultChipElevation(
                defaultElevation = defaultElevation,
                pressedElevation = pressedElevation,
                disabledElevation = disabledElevation,
                clock = clock
            )
        }
    }

    /**
     * Creates a [ButtonColors] that represents the default background and content colors used in
     * an [OutlinedButton].
     *
     * @param backgroundColor the background color of this [OutlinedButton]
     * @param contentColor the content color of this [OutlinedButton] when enabled
     * @param disabledContentColor the content color of this [OutlinedButton] when not enabled
     */
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun defaultOutlinedChipColors(
        selectedContentColor: Color = MaterialTheme.colors.primary,
        unselectedContentColor: Color = MaterialTheme.colors.secondary,
        selectedBackgroundColor: Color = MaterialTheme.colors.surface,
        unselectedBackgroundColor: Color = MaterialTheme.colors.surface,
        selectedDisabledContentColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        unselectedDisabledContentColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
        selectedDisabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        unselectedDisabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
    ): ChipColors = DefaultChipColors(
        selectedBackgroundColor = selectedBackgroundColor,
        unselectedBackgroundColor = unselectedBackgroundColor,
        disabledSelectedBackgroundColor = selectedDisabledBackgroundColor,
        disabledUnselectedBackgroundColor = unselectedDisabledBackgroundColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        selectedDisabledContentColor = selectedDisabledContentColor,
        unselectedDisabledContentColor = unselectedDisabledContentColor,
    )

    val DefaultContentPadding = PaddingValues(
        start = HorizontalPadding,
        top = VerticalPadding,
        end = HorizontalPadding,
        bottom = VerticalPadding
    )

    /**
     * The default [OutlinedButton]'s border size
     */
    private val OutlinedBorderSize = 1.dp

    /**
     * The default disabled content color used by all types of [Button]s
     */
    @Composable
    val defaultOutlinedBorder: BorderStroke
        get() = BorderStroke(
            OutlinedBorderSize, MaterialTheme.colors.onSurface.copy(alpha = ButtonConstants.OutlinedBorderOpacity)
        )

}

/**
 * Default [ButtonColors] implementation.
 */
@OptIn(ExperimentalMaterialApi::class)
@Immutable
private data class DefaultChipColors(
    private val selectedBackgroundColor: Color,
    private val unselectedBackgroundColor: Color,
    private val disabledSelectedBackgroundColor: Color,
    private val disabledUnselectedBackgroundColor: Color,
    private val selectedContentColor: Color,
    private val unselectedContentColor: Color,
    private val selectedDisabledContentColor: Color,
    private val unselectedDisabledContentColor: Color,
) : ChipColors {

    override fun backgroundColor(enabled: Boolean, selected: Boolean): Color {
        return when {
            enabled && selected -> selectedBackgroundColor
            enabled && !selected -> unselectedBackgroundColor
            !enabled && selected -> disabledSelectedBackgroundColor
            !enabled && !selected -> disabledUnselectedBackgroundColor
            else -> selectedBackgroundColor
        }
    }

    override fun contentColor(enabled: Boolean, selected: Boolean): Color {
        return when {
            enabled && selected -> selectedContentColor
            enabled && !selected -> unselectedContentColor
            !enabled && selected -> selectedDisabledContentColor
            !enabled && !selected -> unselectedDisabledContentColor
            else -> selectedContentColor
        }
    }
}


/**
 * Default [ButtonElevation] implementation.
 */
@OptIn(ExperimentalMaterialApi::class)
@Stable
private class DefaultChipElevation(
    private val defaultElevation: Dp,
    private val pressedElevation: Dp,
    private val disabledElevation: Dp,
    private val clock: AnimationClockObservable
) : ChipElevation {
    private val lazyAnimatedElevation = LazyAnimatedValue<Dp, AnimationVector1D> { target ->
        AnimatedValueModel(initialValue = target, typeConverter = Dp.VectorConverter, clock = clock)
    }

    override fun elevation(enabled: Boolean, interactionState: InteractionState): Dp {
        val interaction = interactionState.value.lastOrNull {
            it is Interaction.Pressed
        }

        val target = if (!enabled) {
            disabledElevation
        } else {
            when (interaction) {
                Interaction.Pressed -> pressedElevation
                else -> defaultElevation
            }
        }

        val animatedElevation = lazyAnimatedElevation.animatedValueForTarget(target)

        if (animatedElevation.targetValue != target) {
            if (!enabled) {
                // No transition when moving to a disabled state
                animatedElevation.snapTo(target)
            } else {
                val lastInteraction = when (animatedElevation.targetValue) {
                    pressedElevation -> Interaction.Pressed
                    else -> null
                }
                animatedElevation.animateElevation(
                    from = lastInteraction,
                    to = interaction,
                    target = target
                )
            }
        }

        return animatedElevation.value
    }
}

/**
 * A lazy wrapper around [AnimatedValue] that delays creating the [AnimatedValue] until the
 * initial value / target is known. This is similar to [androidx.compose.animation.animate], but
 * can be used outside of a Composable function.
 *
 * @property factory lazily invoked factory to create an [AnimatedValue] for the given target
 */
internal class LazyAnimatedValue<T, V : AnimationVector>(
    private val factory: (target: T) -> AnimatedValue<T, V>
) {
    private var animatedValue: AnimatedValue<T, V>? = null

    /**
     * @return a new [AnimatedValue] with an initial value equal to [targetValue], or the
     * existing [AnimatedValue] if it has already been created.
     */
    fun animatedValueForTarget(targetValue: T): AnimatedValue<T, V> {
        return animatedValue ?: factory(targetValue).also { animatedValue = it }
    }
}

@ExperimentalMaterialApi
@Stable
interface ChipColors {
    /**
     * Represents the background color for this button, depending on [enabled].
     *
     * @param enabled whether the button is enabled
     */
    fun backgroundColor(enabled: Boolean, selected: Boolean): Color

    /**
     * Represents the content color for this button, depending on [enabled].
     *
     * @param enabled whether the button is enabled
     */
    fun contentColor(enabled: Boolean, selected: Boolean): Color
}
