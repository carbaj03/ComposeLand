package com.acv.composeland.button

import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.gestures.draggable
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.navigation.NavHostController
import com.acv.composeland.common.CodeScaffold

@Composable
fun ButtonInteractionState(goBack : () -> Unit) {
    val interactionState = remember { InteractionState() }
    var text = when {
        Interaction.Dragged in interactionState -> "Dragged"
        Interaction.Pressed in interactionState -> "Pressed"
        // Default / baseline state
        else -> "Drag me horizontally, or press me!"
    }
    val draggable = Modifier.draggable(
        orientation = Orientation.Horizontal,
        interactionState = interactionState
    ) { /* update some business state here */ }

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
        goBack = goBack,
        code = code
    ) {
        Button(
            modifier = draggable,
            onClick = { },
            interactionState = interactionState,
        ) {
            Text(text = text)
        }
    }
}