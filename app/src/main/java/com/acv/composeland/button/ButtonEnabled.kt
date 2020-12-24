package com.acv.composeland.button

import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.acv.composeland.common.CodeScaffold

@Composable
fun ButtonEnabled(goBack : () -> Unit) {
    var count by remember { mutableStateOf(0) }
    var switch by remember { mutableStateOf(false) }

    val code: String = """
      @Composable
      fun ButtonSample(){
        var count by remember { mutableStateOf(0) }
        var switch by remember { mutableStateOf(false) }
        Button(
            onClick = { count++ },
            enabled = switch,
        ) {
            Text("$count clicks")
        }
      }
    """

    CodeScaffold(
        goBack = goBack,
        code = code
    ) {
        Switch(checked = switch, onCheckedChange = { switch = !switch })
        Button(
            onClick = { count++ },
            enabled = switch,
        ) {
            Text("$count clicks")
        }
    }
}