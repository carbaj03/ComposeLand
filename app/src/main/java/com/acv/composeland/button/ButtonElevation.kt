package com.acv.composeland.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.acv.composeland.common.CodeScaffold

@Composable
fun ButtonElevation(goBack : () -> Unit) {
    var count by remember { mutableStateOf(0) }
    var default by remember { mutableStateOf(0) }
    var disabled by remember { mutableStateOf(0) }
    var pressed by remember { mutableStateOf(0) }

    val code: String = """
      @Composable
      fun ButtonSample(){
        var count by remember { mutableStateOf(0) }
        Button(
            onClick = { count++ },
        ) {
            Text("$count clicks")
        }
      }
    """

    CodeScaffold(
        goBack = goBack,
        code = code
    ) {
        Button(
            onClick = { count++ },
            elevation = ButtonConstants.defaultElevation(
                defaultElevation = default.dp,
                disabledElevation = disabled.dp,
                pressedElevation = pressed.dp,
            ),
        ) {
            Text("$count clicks")
        }
    }
}