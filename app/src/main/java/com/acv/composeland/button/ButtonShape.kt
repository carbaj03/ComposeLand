package com.acv.composeland.button

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Shape
import androidx.navigation.NavHostController
import com.acv.composeland.common.ChipGroup
import com.acv.composeland.common.CodeScaffold
import java.util.*

data class ButtonShapeState(
    val goBack: () -> Unit,
)

@Composable
fun ButtonShape(
    navController: NavHostController
) {
    val state = ButtonShapeState(
        goBack = { navController.popBackStack() },
    )
    val a: HashMap<String, Shape> = hashMapOf(
        "Small" to MaterialTheme.shapes.small,
        "Medium" to MaterialTheme.shapes.medium,
        "Large" to MaterialTheme.shapes.large,
    )
    var switch by remember { mutableStateOf("Small") }

    val code: String = """
      @Composable
      fun ButtonSample(){
        var count by remember { mutableStateOf(0) }
        var switch by remember { mutableStateOf(false) }
        Button(
            onClick = { count++ },
            enabled = switch,
        ) {
            Text("$switch clicks")
        }
      }
    """

    CodeScaffold(
        goBack = state.goBack,
        code = code
    ) {
        ChipGroup(
            items = a.keys.toList(),
            onChange = { switch = it }
        )
        Button(
            onClick = { },
            shape = a[switch]!!,
        ) {
            Text("Sample")
        }
    }
}