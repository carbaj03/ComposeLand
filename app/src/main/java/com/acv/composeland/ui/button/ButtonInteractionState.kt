package com.acv.composeland.ui.button

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.acv.composeland.suspend.launch
import com.acv.composeland.ui.common.CodeScaffold
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

data class ButtonInteractionStateState(
    val goBack: () -> Unit,
)

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ButtonInteractionState(
    navController: NavHostController
) {
    val state = ButtonInteractionStateState(
        goBack = { navController.popBackStack() },
    )
    val scope = rememberCoroutineScope()
    val interactionState = remember { MutableInteractionSource() }
    var text by remember { mutableStateOf("") }

    scope.launch {
        interactionState.interactions.collect {
            text = when (it) {
                is PressInteraction.Press -> "Dragged"
                else -> "Drag me horizontally, or press me!"
            }
        }
    }
    val draggable = Modifier.draggable(
        state = rememberDraggableState(onDelta = { 0f }),
        orientation = Orientation.Horizontal,
        interactionSource = interactionState,
    )
    val code: String = """
      @Composable
      fun ButtonSample(){
        var count by remember { mutableStateOf(0) }
        var switch by remember { mutableStateOf(false) }
        Button(
            onClick = { count++ },
            enabled = switch,
        ) {
            Text("")
        }
      }
    """

    CodeScaffold(
        title = "Button Interaction State",
        goBack = state.goBack,
        code = code
    ) {
        Button(
            modifier = draggable,
            onClick = { },
            interactionSource = interactionState,
        ) {
            Text(text = text)
        }
    }
}