package com.acv.composeland.ui.animation

import androidx.compose.animation.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import com.acv.composeland.ui.common.Argument
import com.acv.composeland.ui.common.ChipGroup
import com.acv.composeland.ui.common.CodeScaffold
import com.acv.composeland.ui.common.codeBui

data class AnimationVisibilityState(
    val goBack: () -> Unit,
)

enum class Visibility {
    Hide, Show
}

enum class Enter {
    fadeIn, slideIn, expandIn, expandHorizontally, expandVertically, slideInHorizontally, slideInVertically
}

enum class Exit {
    fadeOut, slideOut, shrinkOut, shrinkHorizontally, shrinkVertically, slideOutHorizontally, slideOutVertically
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationVisibility(
    navController: NavHostController
) {
    val state = AnimationVisibilityState(
        goBack = { navController.popBackStack() },
    )

    val visibilityState: Map<String, Visibility> = Visibility.values().map { it.name to it }.toMap()
    val enterState: Map<String, Enter> = Enter.values().map { it.name to it }.toMap()
    val exitState: Map<String, Exit> = Exit.values().map { it.name to it }.toMap()

    var visible by remember { mutableStateOf(Visibility.Show) }
    var enter by remember { mutableStateOf(Enter.fadeIn) }
    var exit by remember { mutableStateOf(Exit.fadeOut) }

    val code: AnnotatedString = codeBui {
        annotation(name = "Composable")
        function(name = "TextColor") {
            varString(name = "clicks")
            `class`(
                name = "AnimatedVisibility",
                Argument("visible", visible.name),
                Argument(
                    name = "enter",
                    value = enter.name
                ),
                Argument(
                    name = "exit",
                    value = exit.name
                ),
            )
        }
    }

    CodeScaffold(
        title = "Button Color",
        goBack = state.goBack,
        code = code,
        sample = {
            AnimatedVisibility(
                visible = visible == Visibility.Show,
                enter = enter.toCompose(),
                exit = exit.toCompose(),
            ) {
                Text(text = "Animation Visibility example")
            }
        },
        options = {
            ChipGroup(
                items = visibilityState.keys.toList(),
                onChange = { visible = Visibility.valueOf(it) }
            )
            ChipGroup(
                items = enterState.keys.toList(),
                onChange = { enter = Enter.valueOf(it) }
            )
            ChipGroup(
                items = exitState.keys.toList(),
                onChange = { exit = Exit.valueOf(it) }
            )
        },
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Enter.toCompose(): EnterTransition =
    when (this) {
        Enter.fadeIn -> fadeIn()
        Enter.slideIn -> slideIn({ fullSize -> IntOffset(fullSize.width / 4, 100) })
        Enter.expandIn -> expandIn()
        Enter.expandHorizontally -> expandHorizontally()
        Enter.expandVertically -> expandVertically()
        Enter.slideInHorizontally -> slideInHorizontally()
        Enter.slideInVertically -> slideInVertically()
    }

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Exit.toCompose() =
    when (this) {
        Exit.fadeOut -> fadeOut()
        Exit.slideOut -> slideOut({ IntOffset(-180, 50) })
        Exit.shrinkOut -> shrinkOut()
        Exit.shrinkHorizontally -> shrinkHorizontally()
        Exit.shrinkVertically -> shrinkVertically()
        Exit.slideOutHorizontally -> slideOutHorizontally()
        Exit.slideOutVertically -> slideOutVertically()
    }